package model;

import java.sql.Date;

public class Menu {
    private String product_id;
    private String product_name;
    private String type;
    private Integer stock;
    private String image;
    private Date date;

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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
}
