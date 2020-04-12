package com.cellterion.dealerservice.service;

import com.cellterion.dealerservice.model.Dealer;

import java.util.List;

public interface DealerService {

    Dealer saveDealer(Dealer dealer);

    Dealer findDealerById(Integer dealerId);

    List<Dealer> findAllDealers();

    Dealer findDealerByDealerName(String dealerName);

}
