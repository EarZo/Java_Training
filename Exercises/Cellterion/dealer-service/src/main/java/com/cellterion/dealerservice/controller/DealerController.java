package com.cellterion.dealerservice.controller;

import com.cellterion.dealerservice.model.Dealer;
import com.cellterion.dealerservice.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class DealerController {

    @Autowired
    DealerService dealerService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Dealer-Service!";
    }

    @PostMapping("/dealer")
    public Dealer saveDealer(@RequestBody Dealer dealer) {
        return dealerService.saveDealer(dealer);
    }

    @GetMapping("/users")
    public List<Dealer> getAllDealers() {
        return dealerService.findAllDealers();
    }

    @GetMapping("/user/{id}")
    public Dealer getDealer(@PathVariable Integer id) {
        return dealerService.findDealerById(id);
    }

}
