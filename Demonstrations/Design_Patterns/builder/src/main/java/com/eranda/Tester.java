package com.eranda;

public class Tester {

    public static void main(String[] args) {

        Booking.Room room = new Booking.Room(2, 1);
        Booking booking = room.isAirConditioned(true).meal("Breakfast").book();

        System.out.println(booking);

    }

}
