package com.eranda.offering;

import com.eranda.food.Dessert;
import com.eranda.food.Pizza;

public class Kids extends Offer {

    @Override
    protected void createOffering() {
        foodType.add(new Pizza());
        foodType.add(new Dessert());
    }
}
