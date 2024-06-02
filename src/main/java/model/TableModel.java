package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableModel {
    private SimpleIntegerProperty number;
    private SimpleStringProperty status;

    public TableModel() {

    }

    public TableModel(int number, String status) {
        this.number = new SimpleIntegerProperty(number);
        this.status = new SimpleStringProperty(status);
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        if (this.number == null) {
            this.number = new SimpleIntegerProperty();
        }
        this.number.set(number);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        if (this.status == null) {
            this.status = new SimpleStringProperty();
        }
        this.status.set(status);
    }
}
