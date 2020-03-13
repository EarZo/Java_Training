package com.cellterion.dealerservice.service;

import com.cellterion.dealerservice.model.Dealer;
import com.cellterion.dealerservice.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public Dealer saveDealer(Dealer dealer) {
        return dealerRepository.save(dealer);
    }

    @Override
    public Dealer findDealerById(Integer dealerId) {
        Optional<Dealer> dealerOptional = dealerRepository.findById(dealerId);

        if(dealerOptional.isPresent())
            return dealerOptional.get();
        return null;
    }

    @Override
    public List<Dealer> findAllDealers(){
        return dealerRepository.findAll();
    }

    @Override
    public List<Dealer> findDealersBySmartphoneId(Integer id) {
        return dealerRepository.findDealersBySmartphoneId(id);
    }
}
