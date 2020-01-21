package com.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Bookingproducts} entity.
 */
public class BookingproductsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer bookingid;

    @NotNull
    private Integer productid;

    @Min(value = 0)
    private Integer bookingqty;


    private Long bookingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getBookingqty() {
        return bookingqty;
    }

    public void setBookingqty(Integer bookingqty) {
        this.bookingqty = bookingqty;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingproductsDTO bookingproductsDTO = (BookingproductsDTO) o;
        if (bookingproductsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingproductsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingproductsDTO{" +
            "id=" + getId() +
            ", bookingid=" + getBookingid() +
            ", productid=" + getProductid() +
            ", bookingqty=" + getBookingqty() +
            ", bookingId=" + getBookingId() +
            "}";
    }
}
