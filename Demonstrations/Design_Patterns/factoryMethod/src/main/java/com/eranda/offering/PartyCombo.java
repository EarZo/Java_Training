package com.eranda.offering;

import com.eranda.food.Appetizer;
import com.eranda.food.Calzone;
import com.eranda.food.Dessert;
import com.eranda.food.Pizza;

public class PartyCombo extends Offer {

    @Override
    protected void createOffering() {
        foodType.add(new Appetizer());
        foodType.add(new Calzone());
        foodType.add(new Pizza());
        foodType.add(new Dessert());
    }
}
