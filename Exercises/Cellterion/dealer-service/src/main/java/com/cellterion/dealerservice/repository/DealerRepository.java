package com.cellterion.dealerservice.repository;

import com.cellterion.dealerservice.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface DealerRepository extends JpaRepository<Dealer, Integer> {
    Dealer findDealerByDealerName(String dealerName);
}
