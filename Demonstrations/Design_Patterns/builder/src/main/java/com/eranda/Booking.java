package com.eranda;

public class Booking {

    private final int numberOfNights;
    private final int numberOfBeds;
    private final boolean isAirConditioned;
    private final String meal;

    public Booking(Room room) {
        this.numberOfNights = room.numberOfNights;
        this.numberOfBeds = room.numberOfBeds;
        this.isAirConditioned = room.isAirConditioned;
        this.meal = room.meal;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "numberOfNights=" + numberOfNights +
                ", numberOfBeds=" + numberOfBeds +
                ", isAirConditioned=" + isAirConditioned +
                ", meal='" + meal + '\'' +
                '}';
    }

    //this is the builder class
    static class Room{
        private int numberOfNights;
        private int numberOfBeds;
        private boolean isAirConditioned;
        private String meal;

        //this is the build() method
        public Booking book() {
            return new Booking(this);
        }

        public Room(int numberOfNights, int numberOfBeds) {
            this.numberOfNights = numberOfNights;
            this.numberOfBeds = numberOfBeds;
        }

        public Room isAirConditioned(boolean isAirConditioned){
            this.isAirConditioned = isAirConditioned;
            return this;
        }

        public Room meal(String meal){
            this.meal = meal;
            return this;
        }
    }
}
