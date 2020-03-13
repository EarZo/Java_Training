package com.cellterion.dealerservice.repository;

import com.cellterion.dealerservice.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Integer> {

        @Query("SELECT DISTINCT new Dealer(de.dealerId,de.dealerName) FROM Dealer de JOIN de.dealerSmartphones ds WHERE ds.smartphoneId = ?1")
        List<Dealer> findDealersBySmartphoneId(Integer id);

}
