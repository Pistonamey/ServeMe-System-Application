package com.example.myapplication;

public class Vendor {


    String vendorName, vendorEmail, vendorNumber, vendorAddress, vendorPassword, vendorServiceType, type;

    int oneStarCount,twoStarCount,threeStarCount,fourStarCount,fiveStarCount;

    double vendorScore;

    public Vendor()
    {

    }


    public Vendor(String vendorName, String vendorEmail, String vendorNumber, String vendorAddress, String vendorPassword, String vendorServiceType, String type, int oneStarCount, int twoStarCount, int threeStarCount, int fourStarCount, int fiveStarCount, double vendorScore)
    {
        this.vendorName = vendorName;
        this.vendorEmail = vendorEmail;
        this.vendorNumber = vendorNumber;
        this.vendorAddress = vendorAddress;
        this.vendorPassword = vendorPassword;
        this.vendorServiceType = vendorServiceType;
        this.type = type;
        this.vendorScore = vendorScore;
        this.oneStarCount = oneStarCount;
        this.twoStarCount = twoStarCount;
        this.threeStarCount = threeStarCount;
        this.fourStarCount = fourStarCount;
        this.fiveStarCount = fiveStarCount;
    }


    public int getOneStarCount() {
        return oneStarCount;
    }

    public void setOneStarCount(int oneStarCount) {
        this.oneStarCount = oneStarCount;
    }

    public int getTwoStarCount() {
        return twoStarCount;
    }

    public void setTwoStarCount(int twoStarCount) {
        this.twoStarCount = twoStarCount;
    }

    public int getThreeStarCount() {
        return threeStarCount;
    }

    public void setThreeStarCount(int threeStarCount) {
        this.threeStarCount = threeStarCount;
    }

    public int getFourStarCount() {
        return fourStarCount;
    }

    public void setFourStarCount(int fourStarCount) {
        this.fourStarCount = fourStarCount;
    }

    public int getFiveStarCount() {
        return fiveStarCount;
    }

    public void setFiveStarCount(int fiveStarCount) {
        this.fiveStarCount = fiveStarCount;
    }

    public double getVendorScore() {
        return vendorScore;
    }

    public void setVendorScore(double vendorScore) {
        this.vendorScore = vendorScore;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    public String getVendorNumber() {
        return vendorNumber;
    }

    public void setVendorNumber(String vendorNumber) {
        this.vendorNumber = vendorNumber;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }

    public String getVendorServiceType() {
        return vendorServiceType;
    }

    public void setVendorServiceType(String vendorServiceType) {
        this.vendorServiceType = vendorServiceType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
