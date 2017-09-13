package com.jszybisty.marketcheckout.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private BigDecimal price;

    private Long specialPriceQuantity;

    private BigDecimal specialPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getSpecialPriceQuantity() {
        return specialPriceQuantity;
    }

    public void setSpecialPriceQuantity(Long specialPriceQuantity) {
        this.specialPriceQuantity = specialPriceQuantity;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (specialPriceQuantity != null ? !specialPriceQuantity.equals(item.specialPriceQuantity) : item.specialPriceQuantity != null)
            return false;
        return specialPrice != null ? specialPrice.equals(item.specialPrice) : item.specialPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (specialPriceQuantity != null ? specialPriceQuantity.hashCode() : 0);
        result = 31 * result + (specialPrice != null ? specialPrice.hashCode() : 0);
        return result;
    }
}
