package com.wf.dcs.app.dto;

import java.math.BigDecimal;

/**
 * Created by rbandioque on 11/17/16.
 */
public class InventoryItemDto extends BaseDto {
    private String code;
    private String description;
    private BigDecimal price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
