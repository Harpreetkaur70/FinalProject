package com.example.pizza_ordering.ui.home;

public class getset {
    int id;
    int orderid;
    private String name;
    private double price;

    public getset() {
    }

    public getset(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public getset(int id, String name, double price) {
        this.id=id;
        this.name=name;
        this.price=price;
    }

    public getset(int id, int orderid, String name, double price) {
        this.id = id;
        this.orderid = orderid;
        this.name = name;
        this.price = price;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
