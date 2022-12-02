package com.example.demo.Parsers;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CompanyInfo {
    private ArrayList<String> logos;
    private ArrayList<String> icons;
    private String name;
    private String url;
    private String facebook;
    private String twitter;
    private int employees;
    private String address;

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "name='" + name + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                ", logos=" + logos +
                ", icons=" + icons +
                ", employees=" + employees +
                ", address='" + address + '\'' +
                '}';
    }

    public ArrayList<String> getLogos() {
        return logos;
    }
    public ArrayList<String> getIcons() {
        return icons;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public int getEmployees() {
        return employees;
    }

    public String getAddress() {
        return address;
    }

    public void setLogos(ArrayList<String> logos) {
        this.logos = logos;
    }

    public void setIcons(ArrayList<String> icons) {
        this.icons = icons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CompanyInfo(){
        name = "";
        url = "";
        facebook = "";
        twitter = "";
        logos = new ArrayList<>();
        icons = new ArrayList<>();
        employees = 0;
        address = "";
    }
}
