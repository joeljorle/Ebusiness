package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.domain.enumeration.Shippingstate;

import com.domain.enumeration.Evenementstate;

/**
 * A Bookingdetails.
 */
@Entity
@Table(name = "bookingdetails")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bookingdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "total")
    private Double total;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "shipping")
    private Double shipping;

    @Enumerated(EnumType.STRING)
    @Column(name = "shippingstate")
    private Shippingstate shippingstate;

    @Column(name = "about")
    private String about;

    @Column(name = "couponconde")
    private String couponconde;

    @Lob
    @Column(name = "qrcode")
    private String qrcode;

    @Column(name = "code")
    private String code;

    @Column(name = "url")
    private String url;

    @Column(name = "token")
    private String token;

    @Column(name = "paymentmode")
    private String paymentmode;

    @Column(name = "startdate")
    private Instant startdate;

    @Column(name = "enddate")
    private Instant enddate;

    @Enumerated(EnumType.STRING)
    @Column(name = "enventstate")
    private Evenementstate enventstate;

    @Column(name = "places")
    private Integer places;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Bookingdetails subtotal(Double subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotal() {
        return total;
    }

    public Bookingdetails total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getTax() {
        return tax;
    }

    public Bookingdetails tax(Integer tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Double getShipping() {
        return shipping;
    }

    public Bookingdetails shipping(Double shipping) {
        this.shipping = shipping;
        return this;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public Shippingstate getShippingstate() {
        return shippingstate;
    }

    public Bookingdetails shippingstate(Shippingstate shippingstate) {
        this.shippingstate = shippingstate;
        return this;
    }

    public void setShippingstate(Shippingstate shippingstate) {
        this.shippingstate = shippingstate;
    }

    public String getAbout() {
        return about;
    }

    public Bookingdetails about(String about) {
        this.about = about;
        return this;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCouponconde() {
        return couponconde;
    }

    public Bookingdetails couponconde(String couponconde) {
        this.couponconde = couponconde;
        return this;
    }

    public void setCouponconde(String couponconde) {
        this.couponconde = couponconde;
    }

    public String getQrcode() {
        return qrcode;
    }

    public Bookingdetails qrcode(String qrcode) {
        this.qrcode = qrcode;
        return this;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getCode() {
        return code;
    }

    public Bookingdetails code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public Bookingdetails url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public Bookingdetails token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPaymentmode() {
        return paymentmode;
    }

    public Bookingdetails paymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
        return this;
    }

    public void setPaymentmode(String paymentmode) {
        this.paymentmode = paymentmode;
    }

    public Instant getStartdate() {
        return startdate;
    }

    public Bookingdetails startdate(Instant startdate) {
        this.startdate = startdate;
        return this;
    }

    public void setStartdate(Instant startdate) {
        this.startdate = startdate;
    }

    public Instant getEnddate() {
        return enddate;
    }

    public Bookingdetails enddate(Instant enddate) {
        this.enddate = enddate;
        return this;
    }

    public void setEnddate(Instant enddate) {
        this.enddate = enddate;
    }

    public Evenementstate getEnventstate() {
        return enventstate;
    }

    public Bookingdetails enventstate(Evenementstate enventstate) {
        this.enventstate = enventstate;
        return this;
    }

    public void setEnventstate(Evenementstate enventstate) {
        this.enventstate = enventstate;
    }

    public Integer getPlaces() {
        return places;
    }

    public Bookingdetails places(Integer places) {
        this.places = places;
        return this;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bookingdetails)) {
            return false;
        }
        return id != null && id.equals(((Bookingdetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bookingdetails{" +
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
