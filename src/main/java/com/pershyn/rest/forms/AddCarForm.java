package com.pershyn.rest.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddCarForm {
    @NotBlank
    @Size(min=2, max = 60)
    private String name;

    @NotBlank
    @Size(min=2, max = 60)
    private int year;

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
}
