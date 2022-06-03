package com.example.lostfoundmap.model;

import androidx.annotation.Nullable;

public class LostFoundMod {
    private Integer id;
    private String type;
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;
    private Double latitude;
    private Double longitude;

    public LostFoundMod(String type, String textName, String textPhone,
                        String textDescription, String textDate, String textLocation,
                        Double latitude, Double longitude) {
        this.id = id;
        this.type = type;
        this.name = textName;
        this.phone = textPhone;
        this.description = textDescription;
        this.date = textDate;
        this.location = textLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LostFoundMod() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
