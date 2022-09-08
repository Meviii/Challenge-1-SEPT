package com.mevlut.onlineshop.model;


import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    private static final AtomicInteger idCount = new AtomicInteger(0);
    private int id;
    private String name;
    private String desc;
    private double price;

    public Item(){
        this.id = idCount.incrementAndGet();
    }

    public Item(String name, String desc, double price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.id = idCount.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                '}';
    }
}
