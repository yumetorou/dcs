package com.wf.dcs.app.model;

import com.wf.dcs.app.model.base.BaseModel;
import org.hibernate.annotations.Type;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by rbandioque on 11/15/16.
 */
@Entity(name = "Customer")
public class Customer extends BaseModel {

    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "GROUPING", nullable = false)
    private String group;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @ElementCollection
    @CollectionTable(name = "CUSTOMER_CONTACTS", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    private Set<String> contactNumbers;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Column(name = "FIRST_BUY_DATE")
    private LocalDateTime firstBuyDate;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @ElementCollection
    @CollectionTable(name = "CUSTOMER_DELIVERY_DAYS", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    private Set<Days> deliveryDay;

    @Column(name = "WEEKLY_QUANTITY")
    private Long weeklyQuantity;

    @Column(name = "REMARKS")
    private String remarks;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Delivery> deliveries;

    @Column(name = "ACCOUNT_BALANCE")
    private BigDecimal accountBalance;

    @Column(name = "UNRETURNED_BOTTLES")
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


    public LocalDateTime getFirstBuyDate() {
        return firstBuyDate;
    }

    public void setFirstBuyDate(LocalDateTime firstBuyDate) {
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

    public Set<Days> getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(Set<Days> deliveryDay) {
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

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
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

    public Set<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Set<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }
}
