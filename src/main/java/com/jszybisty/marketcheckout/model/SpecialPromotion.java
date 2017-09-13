package com.jszybisty.marketcheckout.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SpecialPromotion {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_item_id")
    private Item firstItem;

    @ManyToOne
    @JoinColumn(name = "second_item_id")
    private Item secondItem;

    private BigDecimal discountPrice;

    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(Item firstItem) {
        this.firstItem = firstItem;
    }

    public Item getSecondItem() {
        return secondItem;
    }

    public void setSecondItem(Item secondItem) {
        this.secondItem = secondItem;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialPromotion that = (SpecialPromotion) o;

        if (isActive != that.isActive) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (firstItem != null ? !firstItem.equals(that.firstItem) : that.firstItem != null) return false;
        if (secondItem != null ? !secondItem.equals(that.secondItem) : that.secondItem != null) return false;
        return discountPrice != null ? discountPrice.equals(that.discountPrice) : that.discountPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstItem != null ? firstItem.hashCode() : 0);
        result = 31 * result + (secondItem != null ? secondItem.hashCode() : 0);
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }
}
