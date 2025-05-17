package com.example.shopping.controller;

import com.example.shopping.Model.Card;
import com.example.shopping.request.InpCard;
import com.example.shopping.response.RespCard;
import com.example.shopping.service.CardService;
import com.example.shopping.service.CardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public List<Card> getCards() {
        return cardService.getAllCards();
    }

    @PostMapping("/cards/add")
    public ResponseEntity<RespCard> addCard(@RequestBody InpCard inpCard) {
        final RespCard respCard = cardService.addProductToCard(inpCard);
        if (respCard != null) {
            return new ResponseEntity<>(respCard, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cards/remove")
    public ResponseEntity<RespCard> removeCard(@RequestBody InpCard inpCard) {
        final RespCard respCard = cardService.removeProductToCard(inpCard);
        if (respCard != null) {
            return new ResponseEntity<>(respCard, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
