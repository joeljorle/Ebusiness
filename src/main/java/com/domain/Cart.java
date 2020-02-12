package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cart.
 */
@Entity
@Table(name = "cart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "totalprice")
    private Double totalprice;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @OneToOne
    @JoinColumn(unique = true)
    private Currency currency;

    @OneToMany(mappedBy = "cart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Cartproducts> cartproducts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("carts")
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public Cart slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Cart categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public Cart userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public Cart totalprice(Double totalprice) {
        this.totalprice = totalprice;
        return this;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Cart createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Cart updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Cart currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Cartproducts> getCartproducts() {
        return cartproducts;
    }

    public Cart cartproducts(Set<Cartproducts> cartproducts) {
        this.cartproducts = cartproducts;
        return this;
    }

    public Cart addCartproducts(Cartproducts cartproducts) {
        this.cartproducts.add(cartproducts);
        cartproducts.setCart(this);
        return this;
    }

    public Cart removeCartproducts(Cartproducts cartproducts) {
        this.cartproducts.remove(cartproducts);
        cartproducts.setCart(null);
        return this;
    }

    public void setCartproducts(Set<Cartproducts> cartproducts) {
        this.cartproducts = cartproducts;
    }

    public Category getCategory() {
        return category;
    }

    public Cart category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cart)) {
            return false;
        }
        return id != null && id.equals(((Cart) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Cart{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", categoryid=" + getCategoryid() +
            ", userid=" + getUserid() +
            ", totalprice=" + getTotalprice() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
