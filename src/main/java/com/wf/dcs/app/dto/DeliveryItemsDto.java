package com.wf.dcs.app.dto;

import java.math.BigDecimal;

/**
 * Created by rbandioque on 11/17/16.
 */
public class DeliveryItemsDto {

    private Long quantity;
    private InventoryItemDto item;
    private BigDecimal total;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public InventoryItemDto getItem() {
        return item;
    }

    public void setItem(InventoryItemDto item) {
        this.item = item;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
