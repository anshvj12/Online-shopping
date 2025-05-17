package com.example.shopping.service;

import com.example.shopping.Model.Card;
import com.example.shopping.request.InpCard;
import com.example.shopping.response.RespCard;

import java.util.List;
import java.util.Optional;

public interface CardService {

    public Optional<Card> getCardById(int id);

    public List<Card> getAllCards();

    public RespCard addProductToCard(InpCard inpCard);

    public RespCard removeProductToCard(InpCard inpCard);

}
