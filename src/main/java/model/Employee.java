package model;

import java.sql.Date;

public class Employee {
    private String name;
    private String phone;
    private String password;
    private Date date;
    private Date job;
    private Integer workingdays;
    private Integer salary;
    private String avatar;
    private String manager_email;

    public Employee(String name, String phone, String password, Date date, Date job, Integer workingdays, Integer salary, String avatar, String manager_email) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.date = date;
        this.job = job;
        this.workingdays = workingdays;
        this.salary = salary;
        this.avatar = avatar;
        this.manager_email = manager_email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getJob() {
        return job;
    }

    public void setJob(Date job) {
        this.job = job;
    }

    public Integer getWorkingdays() {
        return workingdays;
    }

    public void setWorkingdays(Integer workingdays) {
        this.workingdays = workingdays;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getManager_email() {
        return manager_email;
    }

    public void setManager_email(String manager_email) {
        this.manager_email = manager_email;
    }
}

