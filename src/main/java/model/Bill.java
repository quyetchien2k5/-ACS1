package model;

import java.sql.Date;

public class Bill {
    private Integer id;
    private String table_number;
    private Integer total;
    private Date time;

    public Bill(Integer id, String table_number, Integer total, Date time) {
        this.id = id;
        this.table_number = table_number;
        this.total = total;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
