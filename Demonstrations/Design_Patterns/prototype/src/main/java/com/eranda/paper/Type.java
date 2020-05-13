package com.eranda.paper;

public abstract class Type implements Cloneable {

    private String text;
    private double breadthInMillimeters;
    private double lengthInMillimeters;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getBreadthInMillimeters() {
        return breadthInMillimeters;
    }

    public void setBreadthInMillimeters(double breadthInMillimeters) {
        this.breadthInMillimeters = breadthInMillimeters;
    }

    public double getLengthInMillimeters() {
        return lengthInMillimeters;
    }

    public void setLengthInMillimeters(double lengthInMillimeters) {
        this.lengthInMillimeters = lengthInMillimeters;
    }

    @Override
    public String toString() {
        return "Type{" +
                "text='" + text + '\'' +
                ", breadthInMillimeters=" + breadthInMillimeters +
                ", lengthInMillimeters=" + lengthInMillimeters +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
