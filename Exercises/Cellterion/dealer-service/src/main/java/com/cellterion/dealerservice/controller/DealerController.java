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
	public List<Dealer> getDealers(@PathVariable Integer id){
        return dealerService.findDealersBySmartphoneId(id);
//		TypedQuery<DealerSmartphone> query = entityManager.createQuery("SELECT * FROM DealerSmartphone WHERE smartphoneDealersId=" + id, DealerSmartphone.class);
//		return query.getResultList();
	}

}
