package model;


import java.sql.Date;

public class Manager {
    private String name;

    private String email;
    private String gender;
    private Integer age;
    private String password;
    private Date date;

    private Integer salary;
    private String avatar;

    public Manager()
    {
        super();
    }

    public Manager(String name, String email, String gender, Integer age, String password, Date date, Integer salary, String avatar) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.password = password;
        this.date = date;

        this.salary = salary;
        this.avatar = avatar;
    }

public Manager(String email){
        this.email = email;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

}
