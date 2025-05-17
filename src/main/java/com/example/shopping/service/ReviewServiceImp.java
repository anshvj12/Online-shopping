package com.example.shopping.service;

import com.example.shopping.Model.*;
import com.example.shopping.dao.OrderRepository;
import com.example.shopping.dao.ProductRepository;
import com.example.shopping.dao.ReviewRepository;
import com.example.shopping.dao.UserRepository;
import com.example.shopping.request.InpReview;
import com.example.shopping.response.RespReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReviewServiceImp implements ReviewService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImp(ReviewRepository reviewRepository, ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public RespReview addReview(InpReview review) {
        if (review.getProductId() > 0 && review.getUserId() >0)
        {
            final Optional<User> user = userRepository.findById(review.getUserId());
            if (user.isPresent())
            {
                final User getUser = user.get();
                final Optional<Order> order = orderRepository.findById(review.getOrderId());
                if (order.isPresent()) {
                    final Order getOrder = order.get();
                    if (getOrder.getOrderStatus() == Order.Type.DELIVERED) {
                        final Optional<Product> product = productRepository.findById(review.getProductId());
                        if (product.isPresent()) {
                            final Product getProduct = product.get();
                            final Set<OrderProduct> orderProducts = getOrder.getOrderProducts();
                            boolean orderProductStatus = false;

                            for (OrderProduct orderProduct : orderProducts) {
                                if (orderProduct.getProduct().getProductId() == product.get().getProductId()) {
                                    orderProductStatus = true;
                                }
                            }

                            if (orderProductStatus) {
                                Review reviewToAdd = new Review();
                                reviewToAdd.setProduct(getProduct);
                                reviewToAdd.setUser(getUser);

                                if (review.getRating() > 0)
                                    reviewToAdd.setRating(review.getRating());

                                if (review.getReviewText() != null && !review.getReviewText().isEmpty())
                                    reviewToAdd.setReviewText(review.getReviewText());

                                reviewToAdd.setReviewDate(new Date());
                                reviewToAdd.setOrder(getOrder);

                                final Review saveReview = reviewRepository.save(reviewToAdd);

                                if (saveReview != null) {
                                    RespReview respReview = new RespReview();

                                    if (saveReview.getReviewText() != null && !saveReview.getReviewText().isEmpty())
                                        respReview.setReviewText(saveReview.getReviewText());
                                    if (saveReview.getRating() > 0)
                                        respReview.setRating(saveReview.getRating());
                                    respReview.setMessage("Review added successfully on product " + getProduct.getProductName() + " with id " + saveReview.getReviewId());
                                    return respReview;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
