package com.wf.dcs.app.model;

import com.wf.dcs.app.model.base.BaseModel;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rbandioque on 11/15/16.
 */
@Entity(name = "DELIVERY")
public class Delivery extends BaseModel {

    @Column(name = "REFERENCE_NO")
    private String referenceNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "DELIVERY_ITEMS", joinColumns = @JoinColumn(name = "DELIVERY_ID"))
    private List<DeliveryItems> items;

    @Column(name = "TOTAL")
    private BigDecimal totalDelivery;

    @Column(name = "PAYMENT")
    private BigDecimal payment;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "DELIVERED_BOTTLES")
    private Long deliveredBottles;

    @Column(name = "RETURNED_BOTTLES")
    private Long returnedBottles;

    @Column(name = "PAID")
    @Type(type = "yes_no")
    private Boolean paid = Boolean.FALSE;

    @Column(name = "VOID")
    @Type(type = "yes_no")
    private Boolean isVoid = Boolean.FALSE;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<DeliveryItems> getItems() {
        return items;
    }

    public void setItems(List<DeliveryItems> items) {
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

    public Long getDeliveredBottles() {
        return deliveredBottles;
    }

    public void setDeliveredBottles(Long deliveredBottles) {
        this.deliveredBottles = deliveredBottles;
    }

    public Boolean getVoid() {
        return isVoid;
    }

    public void setVoid(Boolean aVoid) {
        isVoid = aVoid;
    }
}