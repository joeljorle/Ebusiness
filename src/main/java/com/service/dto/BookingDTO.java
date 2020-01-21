package com.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Booking} entity.
 */
public class BookingDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    private Integer userid;

    private Integer tourid;

    private Integer tourgroupid;

    private Integer categoryid;

    private Integer productid;

    private Integer evenementid;

    private Instant createdat;

    private Instant updatedat;


    private Long currencyId;

    private Long bookingviewId;

    private Long bookingdetailsId;

    private Long categoryId;

    private Long evenementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTourid() {
        return tourid;
    }

    public void setTourid(Integer tourid) {
        this.tourid = tourid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getBookingviewId() {
        return bookingviewId;
    }

    public void setBookingviewId(Long bookingviewId) {
        this.bookingviewId = bookingviewId;
    }

    public Long getBookingdetailsId() {
        return bookingdetailsId;
    }

    public void setBookingdetailsId(Long bookingdetailsId) {
        this.bookingdetailsId = bookingdetailsId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingDTO bookingDTO = (BookingDTO) o;
        if (bookingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
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
            ", currencyId=" + getCurrencyId() +
            ", bookingviewId=" + getBookingviewId() +
            ", bookingdetailsId=" + getBookingdetailsId() +
            ", categoryId=" + getCategoryId() +
            ", evenementId=" + getEvenementId() +
            "}";
    }
}
