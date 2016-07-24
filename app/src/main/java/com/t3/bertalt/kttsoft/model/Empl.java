package com.t3.bertalt.kttsoft.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by bertalt on 24.07.16.
 */
public class Empl extends RealmObject {

    private static int idGenerator;

    static{
        idGenerator = 1;
    }

     public Empl(){
         this.id = idGenerator++;
     }

    public int getId() {
        return id;
    }

    @PrimaryKey
    private int id;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    private String fullName;
    private int age;
    private double salary;
    private String position;
    private int department;

    @Override
    public String toString() {
        return "Empl{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", department=" + department +
                '}';
    }
}
