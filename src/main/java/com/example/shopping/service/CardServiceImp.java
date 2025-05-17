package com.example.shopping.service;

import com.example.shopping.Model.Card;
import com.example.shopping.Model.CardProduct;
import com.example.shopping.Model.Product;
import com.example.shopping.Model.User;
import com.example.shopping.dao.CardProductRepository;
import com.example.shopping.dao.CardRepository;
import com.example.shopping.request.InpCard;
import com.example.shopping.response.RespCard;
import com.example.shopping.response.RespCardProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CardServiceImp implements CardService {

    private final ProductServiceImp productService;

    private final CardRepository cardRepository;

    private final UserServiceImp userServiceImp;

    private final CardProductRepository cardProductRepository;


    @Autowired
    public CardServiceImp(ProductServiceImp productService, CardRepository cardRepository, UserServiceImp userServiceImp, CardProductRepository cardProductRepository) {
        this.productService = productService;
        this.cardRepository = cardRepository;
        this.userServiceImp = userServiceImp;
        this.cardProductRepository = cardProductRepository;
    }

    @Override
    public Optional<Card> getCardById(int id) {
        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public RespCard addProductToCard(InpCard inpCard) {
        if(inpCard.getUserId() > 0 && inpCard.getProductId() > 0)
        {
            User user = null;
            Card card = null;
            Optional<User> userById = userServiceImp.findUserById(inpCard.getUserId());
            if (userById.isPresent()) {
                 user = userById.get();
                 card = cardRepository.findByUser(user);
                 if (card != null) {
                     Optional<Product> productById = productService.getProductById(inpCard.getProductId());
                     if (productById.isPresent()) {
                         Product product = productById.get();
                         if ( product.getAvailableQuantity() >= inpCard.getQuantity() && inpCard.getQuantity() > 0 ) {

                             final Optional<CardProduct>  cardAndUser = cardProductRepository.findByCardAndUser(card.getCardId(), product.getProductId());

                             if (cardAndUser.isPresent())
                             {
                                 final CardProduct cardProduct1 = cardAndUser.get();
                                 cardProduct1.setQuantity(cardProduct1.getQuantity()+inpCard.getQuantity());
                                 CardProduct save = cardProductRepository.save(cardProduct1);
                             }
                             else
                             {
                                 CardProduct cardProduct = new CardProduct();
                                 cardProduct.setUserCards(card);
                                 cardProduct.setProduct(product);
                                 cardProduct.setQuantity(inpCard.getQuantity());
                                 CardProduct save = cardProductRepository.save(cardProduct);
                             }

                             final Card savedCard = cardRepository.findByUser(user);

                             // Response of Cards
                             RespCard respCard = new RespCard();
                             if (savedCard != null) {
                                 //respCard.setTotalPrice(savedCard.getTotalPrice());
                                 respCard.setExpectedDeliveryDate(savedCard.getExpectedDeliveryDate());
                                 List<RespCardProduct> respCardProducts = new ArrayList<>();
                                 final Set<CardProduct> products = savedCard.getCardProducts();
                                 if (products != null) {
                                     double totalPrice = 0;
                                     for( CardProduct cardProduct1 : products ) {
                                         RespCardProduct respCardProduct = new RespCardProduct();
                                         if(cardProduct1.getProduct().getProductName() != null )
                                         {
                                             respCardProduct.setProductName(cardProduct1.getProduct().getProductName());
                                         }
                                         if(cardProduct1.getQuantity() > 0)
                                         {
                                             respCardProduct.setQuantity(cardProduct1.getQuantity());
                                             totalPrice += (cardProduct1.getProduct().getPrice() * cardProduct1.getQuantity());

                                         }
                                         respCardProducts.add(respCardProduct);
                                     }
                                     respCard.setTotalPrice(totalPrice);
                                 }
                                 respCard.setProducts(respCardProducts);
                                 return respCard;
                             }
                         }
                     }
                 }
            }
        }
        return null;
    }

    @Override
    public RespCard removeProductToCard(InpCard inpCard) {
        if(inpCard.getUserId() > 0 && inpCard.getProductId() > 0)
        {
            User user = null;
            Card card = null;
            Optional<User> userById = userServiceImp.findUserById(inpCard.getUserId());
            if (userById.isPresent()) {
                user = userById.get();
                card = cardRepository.findByUser(user);
                if (card != null) {
                    Optional<Product> productById = productService.getProductById(inpCard.getProductId());
                    if (productById.isPresent()) {
                        Product product = productById.get();

                            final Optional<CardProduct>  cardAndUser = cardProductRepository.findByCardAndUser(card.getCardId(), product.getProductId());

                            if (cardAndUser.isPresent())
                            {
                                final CardProduct cardProduct1 = cardAndUser.get();
                                if(cardProduct1.getQuantity() > inpCard.getQuantity() && inpCard.getQuantity() > 0 )
                                {
                                    cardProduct1.setQuantity(cardProduct1.getQuantity()-inpCard.getQuantity());
                                    CardProduct save = cardProductRepository.save(cardProduct1);
                                }
                                else if (cardProduct1.getQuantity() == inpCard.getQuantity())
                                {
                                    cardProductRepository.deleteByCardProductId(cardProduct1.getCardProductId());
                                }
                                else
                                {

                                    return null;
                                }
                            }
                            else
                            {
                                CardProduct cardProduct = new CardProduct();
                                cardProduct.setUserCards(card);
                                cardProduct.setProduct(product);
                                cardProduct.setQuantity(inpCard.getQuantity());
                                CardProduct save = cardProductRepository.save(cardProduct);
                            }

                            final Card savedCard = cardRepository.findByUser(user);

                            // Response of Cards
                            RespCard respCard = new RespCard();
                            if (savedCard != null) {
                                //respCard.setTotalPrice(savedCard.getTotalPrice());
                                respCard.setExpectedDeliveryDate(savedCard.getExpectedDeliveryDate());
                                List<RespCardProduct> respCardProducts = new ArrayList<>();
                                final Set<CardProduct> products = savedCard.getCardProducts();
                                if (products != null) {
                                    double totalPrice = 0;
                                    for( CardProduct cardProduct1 : products ) {
                                        RespCardProduct respCardProduct = new RespCardProduct();
                                        if(cardProduct1.getProduct().getProductName() != null )
                                        {
                                            respCardProduct.setProductName(cardProduct1.getProduct().getProductName());
                                        }
                                        if(cardProduct1.getQuantity() > 0)
                                        {
                                            respCardProduct.setQuantity(cardProduct1.getQuantity());
                                            totalPrice += (cardProduct1.getProduct().getPrice() * cardProduct1.getQuantity());

                                        }
                                        respCardProducts.add(respCardProduct);
                                    }
                                    respCard.setTotalPrice(totalPrice);
                                }
                                respCard.setProducts(respCardProducts);
                                return respCard;
                            }

                    }
                }
            }
        }
        return null;
    }


}
