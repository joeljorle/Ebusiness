package com.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.domain.enumeration.Shippingstate;
import com.domain.enumeration.Evenementstate;

/**
 * A DTO for the {@link com.domain.Bookingdetails} entity.
 */
public class BookingdetailsDTO implements Serializable {

    private Long id;

    private Double subtotal;

    private Double total;

    private Integer tax;

    private Double shipping;

    private Shippingstate shippingstate;

    private String about;

    private String couponconde;

    @Lob
    private String qrcode;

    private String code;

    private String url;

    private String token;

    private String paymentmode;

    private Instant startdate;

    private Instant enddate;

    private Evenementstate enventstate;

    private Integer places;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public Shippingstate getShippingstate() {
        return shippingstate;
    }

    public void setShippingstate(Shippingstate shippingstate) {
        this.shippingstate = shippingstate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCouponconde() {
        return couponconde;
    }

    public void setCouponconde(String couponconde) {
        this.couponconde = couponconde;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Instant getStartdate() {
        return startdate;
    }

    public void setStartdate(Instant startdate) {
        this.startdate = startdate;
    }

    public Instant getEnddate() {
        return enddate;
    }

    public void setEnddate(Instant enddate) {
        this.enddate = enddate;
    }

    public Evenementstate getEnventstate() {
        return enventstate;
    }

    public void setEnventstate(Evenementstate enventstate) {
        this.enventstate = enventstate;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingdetailsDTO bookingdetailsDTO = (BookingdetailsDTO) o;
        if (bookingdetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingdetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingdetailsDTO{" +
            "id=" + getId() +
            ", subtotal=" + getSubtotal() +
            ", total=" + getTotal() +
            ", tax=" + getTax() +
            ", shipping=" + getShipping() +
            ", shippingstate='" + getShippingstate() + "'" +
            ", about='" + getAbout() + "'" +
            ", couponconde='" + getCouponconde() + "'" +
            ", qrcode='" + getQrcode() + "'" +
            ", code='" + getCode() + "'" +
            ", url='" + getUrl() + "'" +
            ", token='" + getToken() + "'" +
            ", paymentmode='" + getPaymentmode() + "'" +
            ", startdate='" + getStartdate() + "'" +
            ", enddate='" + getEnddate() + "'" +
            ", enventstate='" + getEnventstate() + "'" +
            ", places=" + getPlaces() +
            "}";
    }
}
