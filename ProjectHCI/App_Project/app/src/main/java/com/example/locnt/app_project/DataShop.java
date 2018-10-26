package com.example.locnt.app_project;

public class DataShop {
    private String name,address,phone,time;
    private int img,price;
    private boolean isFooter;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public DataShop(String name, int img) {

        this.name = name;
        this.img = img;
    }

    public DataShop(String name, String address, String phone, String time, int img, int price) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.time = time;
        this.img = img;
        this.price = price;
    }

    public DataShop() {
    }

    public boolean isFooter() {
        return isFooter;
    }

    public void setFooter(boolean footer) {
        isFooter = footer;
    }
}
