package com.example.boikhuji.Model;

public class dmodel {
    String bimage,name,price,quantity,summary,writter;

    public dmodel() {
    }

    public dmodel(String bimage, String name, String price, String quantity, String summary, String writter) {
        this.bimage = bimage;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.summary = summary;
        this.writter = writter;
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }
}

