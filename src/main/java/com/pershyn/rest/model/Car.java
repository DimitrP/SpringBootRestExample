package com.pershyn.rest.model;

import javax.persistence.*;
//import javax.xml.bind.annotation.XmlRootElement;


@Entity
//@Table(name = "car")
//@XmlRootElement(name = "pet")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Basic
    @Column(name = "car_name")
    private String name;


    @Basic
    @Column(name = "year")
    private int year;

   @Column(name = "profile_id")
   private Long profile_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }

    public Car(String name, int year, Long profile_id) {
        this.name = name;
        this.year = year;
        this.profile_id = profile_id;
    }

    public Car() {}
}
