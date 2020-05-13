package com.eranda.offering;

import com.eranda.food.Type;

import java.util.ArrayList;
import java.util.List;

public abstract class Offer {

    protected List<Type> foodType = new ArrayList<Type>();

    public Offer() {
        createOffering();
    }

    protected abstract void createOffering();

    @Override
    public String toString() {
        return "Offer{" +
                "foodType=" + foodType +
                '}';
    }
}
