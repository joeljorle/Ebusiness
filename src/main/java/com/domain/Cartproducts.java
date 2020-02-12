package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Cartproducts.
 */
@Entity
@Table(name = "cartproducts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cartproducts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "cartid", nullable = false)
    private Integer cartid;

    @NotNull
    @Column(name = "productid", nullable = false)
    private Integer productid;

    @Min(value = 0)
    @Column(name = "cartqty")
    private Integer cartqty;

    @ManyToOne
    @JsonIgnoreProperties("cartproducts")
    private Cart cart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCartid() {
        return cartid;
    }

    public Cartproducts cartid(Integer cartid) {
        this.cartid = cartid;
        return this;
    }

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Cartproducts productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getCartqty() {
        return cartqty;
    }

    public Cartproducts cartqty(Integer cartqty) {
        this.cartqty = cartqty;
        return this;
    }

    public void setCartqty(Integer cartqty) {
        this.cartqty = cartqty;
    }

    public Cart getCart() {
        return cart;
    }

    public Cartproducts cart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cartproducts)) {
            return false;
        }
        return id != null && id.equals(((Cartproducts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cartproducts{" +
            "id=" + getId() +
            ", cartid=" + getCartid() +
            ", productid=" + getProductid() +
            ", cartqty=" + getCartqty() +
            "}";
    }
}
