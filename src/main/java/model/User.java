package model;


import java.sql.Date;

public class User {
    private String name;

    private String email;
    private String gender;
    private Integer age;
    private String password;
    private String position;
    private Date date;

    private Double salary;
    private String avatar;
    public User()
    {
        super();
    }

    public User(String name, String email, String gender, Integer age, String password, String position, Date date, Double salary, String avatar) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.position = position;
        this.date = date;
        this.salary = salary;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }





    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
