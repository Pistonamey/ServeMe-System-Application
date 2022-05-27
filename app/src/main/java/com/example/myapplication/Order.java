package com.example.myapplication;

public class Order {

    public String custEmail, jobDesc, servDate, servTime, servType, vendEmail, bid_amount, base_pay, per_hour, orderStatus, orderID, total_serv_time, rest_time, work_time, payment_total;
    boolean customerAccepted, vendorAccepted;


    public Order()
    {

    }


    public Order(String custEmail, String jobDesc, String servDate, String servTime, String servType, String vendEmail, String base_pay, String per_hour, String bid_amount, String orderStatus, String orderID, String total_serv_time, String rest_time, String work_time, String payment_total, boolean customerAccepted, boolean vendorAccepted)
    {
        this.custEmail = custEmail;
        this.jobDesc = jobDesc;
        this.servDate = servDate;
        this.servTime = servTime;
        this.servType = servType;
        this.vendEmail = vendEmail;
        this.bid_amount = bid_amount;
        this.customerAccepted = customerAccepted;
        this.base_pay = base_pay;
        this.bid_amount = bid_amount;
        this.per_hour = per_hour;
        this.orderStatus = orderStatus;
        this.orderID = orderID;
        this.vendorAccepted = vendorAccepted;
        this.total_serv_time = total_serv_time;
        this.rest_time = rest_time;
        this.work_time = work_time;
        this.payment_total = payment_total;
    }


    public String getTotal_serv_time() {
        return total_serv_time;
    }

    public void setTotal_serv_time(String total_serv_time) {
        this.total_serv_time = total_serv_time;
    }

    public String getRest_time() {
        return rest_time;
    }

    public void setRest_time(String rest_time) {
        this.rest_time = rest_time;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public String getPayment_total() {
        return payment_total;
    }

    public void setPayment_total(String payment_total) {
        this.payment_total = payment_total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPer_hour() {
        return per_hour;
    }

    public void setPer_hour(String per_hour) {
        this.per_hour = per_hour;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getServDate() {
        return servDate;
    }

    public void setServDate(String servDate) {
        this.servDate = servDate;
    }

    public String getServTime() {
        return servTime;
    }

    public void setServTime(String servTime) {
        this.servTime = servTime;
    }

    public String getServType() {
        return servType;
    }

    public void setServType(String servType) {
        this.servType = servType;
    }

    public String getVendEmail() {
        return vendEmail;
    }

    public void setVendEmail(String vendEmail) {
        this.vendEmail = vendEmail;
    }

    public String getBid_amount() {
        return bid_amount;
    }

    public void setBid_amount(String bid_amount) {
        this.bid_amount = bid_amount;
    }

    public String getBase_pay() {
        return base_pay;
    }

    public void setBase_pay(String base_pay) {
        this.base_pay = base_pay;
    }

    public boolean isCustomerAccepted() {
        return customerAccepted;
    }

    public void setCustomerAccepted(boolean customerAccepted) {
        this.customerAccepted = customerAccepted;
    }

    public boolean isVendorAccepted() {
        return vendorAccepted;
    }

    public void setVendorAccepted(boolean vendorAccepted) {
        this.vendorAccepted = vendorAccepted;
    }
}
