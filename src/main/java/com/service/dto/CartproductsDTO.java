package com.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Cartproducts} entity.
 */
public class CartproductsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer cartid;

    @NotNull
    private Integer productid;

    @Min(value = 0)
    private Integer cartqty;


    private Long cartId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCartid() {
        return cartid;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getCartqty() {
        return cartqty;
    }

    public void setCartqty(Integer cartqty) {
        this.cartqty = cartqty;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartproductsDTO cartproductsDTO = (CartproductsDTO) o;
        if (cartproductsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cartproductsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CartproductsDTO{" +
            "id=" + getId() +
            ", cartid=" + getCartid() +
            ", productid=" + getProductid() +
            ", cartqty=" + getCartqty() +
            ", cartId=" + getCartId() +
            "}";
    }
}
