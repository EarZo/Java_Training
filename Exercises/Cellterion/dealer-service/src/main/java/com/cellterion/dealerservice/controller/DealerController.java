package com.cellterion.dealerservice.controller;

import com.cellterion.dealerservice.model.Address;
import com.cellterion.dealerservice.model.Dealer;
import com.cellterion.dealerservice.model.Telephone;
import com.cellterion.dealerservice.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class DealerController{

    @Autowired
    DealerService dealerService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Dealer-Service!";
    }

    @PostMapping("/dealer")
    public Dealer saveDealer(@RequestBody Dealer dealer) {
        if (dealer.getAddresses() != null) {
            for (Address address : dealer.getAddresses())
                address.setDealer(dealer);
        }

        if (dealer.getTelephoneList() != null) {
            for (Telephone telephone : dealer.getTelephoneList())
                telephone.setDealer(dealer);
        }

        return dealerService.saveDealer(dealer);
    }

    @GetMapping("/dealer/all")
    public List<Dealer> getAllDealers() {
        return dealerService.findAllDealers();
    }

    @GetMapping("/dealer/{dealerId}")
    public Dealer getDealer(@PathVariable Integer dealerId) {
        return dealerService.findDealerById(dealerId);
    }

    @GetMapping("/dealer/name/{dealerName}")
    public Dealer getDealer(@PathVariable String dealerName){
        return dealerService.findDealerByDealerName(dealerName);
    }

}
