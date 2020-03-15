package com.cellterion.dealerservice.controller;

import com.cellterion.dealerservice.model.Dealer;
import com.cellterion.dealerservice.model.DealerSmartphone;
import com.cellterion.dealerservice.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RestController
@RequestMapping("/services")
public class DealerController{

    @Autowired
    DealerService dealerService;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Dealer-Service!";
    }

    @PostMapping("/dealer")
    public Dealer saveDealer(@RequestBody Dealer dealer) {

        if (dealer.getDealerSmartphones() != null) {
            for (DealerSmartphone dealerSmartphone : dealer.getDealerSmartphones())
                dealerSmartphone.setDealer(dealer);
        }

        return dealerService.saveDealer(dealer);
    }

    @GetMapping("/dealers")
    public List<Dealer> getAllDealers() {
        return dealerService.findAllDealers();
    }

    @GetMapping("/dealer/{id}")
    public Dealer getDealer(@PathVariable Integer id) {
        return dealerService.findDealerById(id);
    }

	@GetMapping("/dealerSmartphone/{id}")
	public List getDealers(@PathVariable Integer id){
        return entityManager.createQuery
                ("SELECT DISTINCT d.dealerName, ds.price FROM Dealer d INNER JOIN DealerSmartphone ds ON d.dealerId=ds.dealer.dealerId WHERE smartphoneId = :smartphoneId")
                .setParameter("smartphoneId", id).getResultList();
	}

}
