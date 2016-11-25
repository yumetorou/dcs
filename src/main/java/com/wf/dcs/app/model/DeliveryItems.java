package com.wf.dcs.app.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by rbandioque on 11/15/16.
 */

@Embeddable
public class DeliveryItems {

    @Column(name = "QUANTITY")
    private Long quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID")
    private InventoryItem item;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "ITEM_PRICE")
    private BigDecimal price;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
