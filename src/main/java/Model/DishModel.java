package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DishModel {
    private SimpleIntegerProperty Id;
    private SimpleStringProperty Name;
    private SimpleIntegerProperty Price;
    private SimpleStringProperty Image;
    private SimpleIntegerProperty Number; // Thêm thuộc tính số lượng

    public DishModel(int id, String name, int price, String image) {
        this.Id = new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(name);
        this.Price = new SimpleIntegerProperty(price);
        this.Image = new SimpleStringProperty(image);

    }

    public DishModel( int number, String name, int price) {
        this.Name = new SimpleStringProperty(name);
        this.Price = new SimpleIntegerProperty(price);
        this.Number = new SimpleIntegerProperty(number); // Khởi tạo thuộc tính số lượng
    }
    public DishModel() {}



    public int getIdProduct() {
        return Id.get();
    }

    public SimpleIntegerProperty idProductProperty() {
        return Id;
    }

    public void setIdProduct(int idProduct) {
        if (this.Id == null) {
            this.Id = new SimpleIntegerProperty();
        }
        this.Id.set(idProduct);
    }

    public String getNameProduct() {
        return Name.get();
    }

    public SimpleStringProperty nameProductProperty() {
        return Name;
    }

    public void setNameProduct(String nameProduct) {
        if (this.Name == null) {
            this.Name = new SimpleStringProperty();
        }
        this.Name.set(nameProduct);
    }

    public int getPrice() {
        return Price.get();
    }

    public SimpleIntegerProperty priceProperty() {
        return Price;
    }

    public void setPrice(int price) {
        if (this.Price == null) {
            this.Price = new SimpleIntegerProperty();
        }
        this.Price.set(price);
    }

    public String getImage() {
        return Image.get();
    }

    public SimpleStringProperty imageProperty() {
        return Image;
    }

    public void setImage(String image) {
        if (this.Image == null) {
            this.Image = new SimpleStringProperty();
        }
        this.Image.set(image);
    }

    public int getNumber() {
        return Number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return Number;
    }

    public void setNumber(int number) {
        if (this.Number == null) {
            this.Number = new SimpleIntegerProperty();
        }
        this.Number.set(number);
    }
}
