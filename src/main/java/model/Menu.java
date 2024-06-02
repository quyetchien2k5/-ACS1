package model;

import java.sql.Date;

public class Menu {

    private String product_id;
    private String product_name;
    private String type;
    private Integer stock;
    private String image;
    private Date date;

    private String id;
    private String name;
    private Double price;


    public Menu(){
        super();
    }


    public Menu(String product_id, String product_name, String type, Integer stock, String image, Date date) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.type = type;
        this.stock = stock;
        this.image = image;
        this.date = date;
    }



    public Menu(String id, String name, String type, String image, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;

    }

}
