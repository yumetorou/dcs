package com.wf.dcs.app.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rbandioque on 11/17/16.
 */
public class DeliveryDto extends BaseDto {

    private String referenceNumber;
    private CustomerDeliveryDto customer;
    private List<DeliveryItemsDto> items;
    private BigDecimal totalDelivery;
    private BigDecimal payment;
    private BigDecimal balance;
    private Long deliveredBottles;
    private Long returnedBottles;
    private Boolean paid;
    private Boolean isVoid;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public CustomerDeliveryDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDeliveryDto customer) {
        this.customer = customer;
    }

    public List<DeliveryItemsDto> getItems() {
        return items;
    }

    public void setItems(List<DeliveryItemsDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(BigDecimal totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getDeliveredBottles() {
        return deliveredBottles;
    }

    public void setDeliveredBottles(Long deliveredBottles) {
        this.deliveredBottles = deliveredBottles;
    }

    public Long getReturnedBottles() {
        return returnedBottles;
    }

    public void setReturnedBottles(Long returnedBottles) {
        this.returnedBottles = returnedBottles;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getVoid() {
        return isVoid;
    }

    public void setVoid(Boolean aVoid) {
        isVoid = aVoid;
    }
}
