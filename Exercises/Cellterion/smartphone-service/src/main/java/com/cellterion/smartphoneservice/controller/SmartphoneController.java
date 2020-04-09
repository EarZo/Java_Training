package com.cellterion.smartphoneservice.controller;

import java.util.List;

import com.cellterion.smartphoneservice.model.MainCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cellterion.smartphoneservice.model.Review;
import com.cellterion.smartphoneservice.model.Smartphone;
import com.cellterion.smartphoneservice.service.SmartphoneService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "http://localhost:4200")
public class SmartphoneController {

    @Autowired
    SmartphoneService smartphoneService;

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Smartphone-Service!";
    }

    @PostMapping("/smartphone")
    public Smartphone saveSmartphone(@RequestBody Smartphone smartphone) {
        if (smartphone.getMainCameras() != null) {
            for (MainCamera mainCamera : smartphone.getMainCameras())
                mainCamera.setSmartphone(smartphone);
        }

        if (smartphone.getReviews() != null) {
            for (Review review : smartphone.getReviews())
                review.setSmartphone(smartphone);
        }

        return smartphoneService.saveSmartphone(smartphone);
    }

    @GetMapping("/smartphone/all")
    public List<Smartphone> getAllSmartphones() {
        return smartphoneService.findAllSmartphones();
    }

    @GetMapping("/smartphone/all/{year}")
    public List<Smartphone> getLatestSmartphones(@PathVariable Integer year) {
        return smartphoneService.findLatestSmartphones(year);
    }

    @GetMapping("/smartphone/{id}")
    public ResponseEntity<Smartphone> getSmartphone(@PathVariable Integer id) {
        Smartphone smartphone = smartphoneService.findSmartphoneById(id);

        if (smartphone == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(smartphone);
        }
    }

    @GetMapping("/smartphone/all/brand/{brandName}")
    public Smartphone[] getSmartphonesByBrandName(@PathVariable String brandName) {
        return smartphoneService.getSmartphonesByBrandName(brandName);
    }


//	@GetMapping("/details/{id}")
//	public List getDealers(@PathVariable Integer id){
//		return entityManager.createQuery
//				("SELECT DISTINCT s.brand, s.model, s.fullImage, s.manufactureYear, v.ram, v.memory, v.gpu, v.displayType, v.displaySize, v.displayResolution, v.displayProtection, v.chipset, v.cameraShutter, v.battery FROM Smartphone s INNER JOIN Variant v ON s.smartphoneId=v.smartphone.smartphoneId WHERE smartphoneId = :smartphoneId")
//				.setParameter("smartphoneId", id).getResultList();
//	}


//	public List<Smartphone> getDealersBySmartphoneId(Integer smartphoneId){
//		List<Smartphone> smartphoneList = entityManager.createQuery
//				("SELECT s FROM Smartphone s WHERE s.smartphoneId = :smartphoneId", Smartphone.class)
//				.setParameter("smartphoneId", smartphoneId).getResultList();
//
//		return smartphoneList.toArray(new Smartphone[smartphoneList.size()]);
//	}

}
