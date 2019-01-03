package com.pershyn.rest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private List<Car> cars;

    @OneToMany
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private List<House> houses;

    @OneToMany
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private List<Picture> pictures;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;


    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Profile(List<Car> cars, User user) {
        this.cars = cars;
        this.user = user;
    }

    public Profile(){}
}
