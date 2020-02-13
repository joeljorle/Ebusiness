package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "tourid")
    private Integer tourid;

    @Column(name = "tourgroupid")
    private Integer tourgroupid;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "evenementid")
    private Integer evenementid;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @OneToOne
    @JoinColumn(unique = true)
    private Currency currency;

    @OneToOne
    @JoinColumn(unique = true)
    private Bookingview bookingview;

    @OneToOne
    @JoinColumn(unique = true)
    private Bookingdetails bookingdetails;

    @OneToMany(mappedBy = "booking")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bookingproducts> bookingproducts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Evenement evenement;

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

    public Booking slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Booking userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTourid() {
        return tourid;
    }

    public Booking tourid(Integer tourid) {
        this.tourid = tourid;
        return this;
    }

    public void setTourid(Integer tourid) {
        this.tourid = tourid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public Booking tourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
        return this;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Booking categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Booking productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public Booking evenementid(Integer evenementid) {
        this.evenementid = evenementid;
        return this;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Booking createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Booking updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Booking currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bookingview getBookingview() {
        return bookingview;
    }

    public Booking bookingview(Bookingview bookingview) {
        this.bookingview = bookingview;
        return this;
    }

    public void setBookingview(Bookingview bookingview) {
        this.bookingview = bookingview;
    }

    public Bookingdetails getBookingdetails() {
        return bookingdetails;
    }

    public Booking bookingdetails(Bookingdetails bookingdetails) {
        this.bookingdetails = bookingdetails;
        return this;
    }

    public void setBookingdetails(Bookingdetails bookingdetails) {
        this.bookingdetails = bookingdetails;
    }

    public Set<Bookingproducts> getBookingproducts() {
        return bookingproducts;
    }

    public Booking bookingproducts(Set<Bookingproducts> bookingproducts) {
        this.bookingproducts = bookingproducts;
        return this;
    }

    public Booking addBookingproducts(Bookingproducts bookingproducts) {
        this.bookingproducts.add(bookingproducts);
        bookingproducts.setBooking(this);
        return this;
    }

    public Booking removeBookingproducts(Bookingproducts bookingproducts) {
        this.bookingproducts.remove(bookingproducts);
        bookingproducts.setBooking(null);
        return this;
    }

    public void setBookingproducts(Set<Bookingproducts> bookingproducts) {
        this.bookingproducts = bookingproducts;
    }

    public Category getCategory() {
        return category;
    }

    public Booking category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Booking evenement(Evenement evenement) {
        this.evenement = evenement;
        return this;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        return id != null && id.equals(((Booking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", tourid=" + getTourid() +
            ", tourgroupid=" + getTourgroupid() +
            ", categoryid=" + getCategoryid() +
            ", productid=" + getProductid() +
            ", evenementid=" + getEvenementid() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
