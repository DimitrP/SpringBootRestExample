package com.pershyn.rest.model;

import javax.persistence.*;

@Entity
public class House {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Basic
    @Column
    int year_build;

    @Basic
    @Column
    int bedrooms_number;


    @Basic
    @Column
    int square_footage_number;

    @Basic
    @Column
    int zipcode;

    @Column(name = "profile_id")
    private Long profile_id;

    public House(int year_build, int bedrooms_number, int square_footage_number, int zipcode) {
        this.year_build = year_build;
        this.bedrooms_number = bedrooms_number;
        this.square_footage_number = square_footage_number;
        this.zipcode = zipcode;
    }

    public House() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear_build() {
        return year_build;
    }

    public void setYear_build(int year_build) {
        this.year_build = year_build;
    }

    public int getBedrooms_number() {
        return bedrooms_number;
    }

    public void setBedrooms_number(int bedrooms_number) {
        this.bedrooms_number = bedrooms_number;
    }

    public int getSquare_footage_number() {
        return square_footage_number;
    }

    public void setSquare_footage_number(int square_footage_number) {
        this.square_footage_number = square_footage_number;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Long getProfile_id() {
        return profile_id;
    }


    public void setProfile_id(Long profile_id) {
        this.profile_id = profile_id;
    }


}
