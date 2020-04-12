package com.cellterion.dealerservice.repository;

import com.cellterion.dealerservice.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Integer> {
    Dealer findDealerByDealerName(String dealerName);
}
