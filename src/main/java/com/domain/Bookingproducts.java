package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Bookingproducts.
 */
@Entity
@Table(name = "bookingproducts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bookingproducts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "bookingid", nullable = false)
    private Integer bookingid;

    @NotNull
    @Column(name = "productid", nullable = false)
    private Integer productid;

    @Min(value = 0)
    @Column(name = "bookingqty")
    private Integer bookingqty;

    @ManyToOne
    @JsonIgnoreProperties("bookingproducts")
    private Booking booking;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public Bookingproducts bookingid(Integer bookingid) {
        this.bookingid = bookingid;
        return this;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Bookingproducts productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getBookingqty() {
        return bookingqty;
    }

    public Bookingproducts bookingqty(Integer bookingqty) {
        this.bookingqty = bookingqty;
        return this;
    }

    public void setBookingqty(Integer bookingqty) {
        this.bookingqty = bookingqty;
    }

    public Booking getBooking() {
        return booking;
    }

    public Bookingproducts booking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bookingproducts)) {
            return false;
        }
        return id != null && id.equals(((Bookingproducts) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bookingproducts{" +
            "id=" + getId() +
            ", bookingid=" + getBookingid() +
            ", productid=" + getProductid() +
            ", bookingqty=" + getBookingqty() +
            "}";
    }
}
