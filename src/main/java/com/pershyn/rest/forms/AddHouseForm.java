package com.pershyn.rest.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddHouseForm {


    @NotBlank
    @Size(min=4, max = 4)
    private int yearBuild;

    @NotBlank
    @Size(min=1, max = 2)
    private int bedrooms;

    @NotBlank
    @Size(min=1, max = 5)
    private int sqFeet;


    @NotBlank
    @Size(min=5, max = 5)
    private int zipcode;



    public int getYearBuild() {
        return yearBuild;
    }

    public void setYearBuild(int yearBuild) {
        this.yearBuild = yearBuild;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getSqFeet() {
        return sqFeet;
    }

    public void setSqFeet(int sqFeet) {
        this.sqFeet = sqFeet;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
