package com.wf.dcs.app.dto;

import org.joda.time.LocalDate;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rbandioque on 11/17/16.
 */
public class CustomerDto extends BaseDto {

    private String code;
    private String group;
    private String name;
    private String address;
    private String contactNumber;
    private String alternateContactNumber;
    private LocalDate firstBuyDate;
    private String contactPerson;
    private String deliveryDay;
    private BigDecimal unitPrice;
    private Long weeklyQuantity;
    private String remarks;
    private List<DeliveryDto> deliveries;
    private BigDecimal accountBalance;
    private Long unreturnedBottles;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public LocalDate getFirstBuyDate() {
        return firstBuyDate;
    }

    public void setFirstBuyDate(LocalDate firstBuyDate) {
        this.firstBuyDate = firstBuyDate;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(String deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public Long getWeeklyQuantity() {
        return weeklyQuantity;
    }

    public void setWeeklyQuantity(Long weeklyQuantity) {
        this.weeklyQuantity = weeklyQuantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<DeliveryDto> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryDto> deliveries) {
        this.deliveries = deliveries;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getUnreturnedBottles() {
        return unreturnedBottles;
    }

    public void setUnreturnedBottles(Long unreturnedBottles) {
        this.unreturnedBottles = unreturnedBottles;
    }
}
