package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.domain.enumeration.Tagcolor;

/**
 * A Productdetails.
 */
@Entity
@Table(name = "productdetails")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Productdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "about")
    private String about;

    @Column(name = "title")
    private String title;

    @Column(name = "tag")
    private String tag;

    @Enumerated(EnumType.STRING)
    @Column(name = "tagcolor")
    private Tagcolor tagcolor;

    @Column(name = "defaultlanguage")
    private String defaultlanguage;

    @Column(name = "postalcode")
    private String postalcode;

    @Column(name = "phones")
    private String phones;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "gplus")
    private String gplus;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "instagram")
    private String instagram;

    @Lob
    @Column(name = "opentimes")
    private String opentimes;

    @Lob
    @Column(name = "othercontacts")
    private String othercontacts;

    @Lob
    @Column(name = "otherfields")
    private String otherfields;

    @Lob
    @Column(name = "sizes")
    private String sizes;

    @Lob
    @Column(name = "colors")
    private String colors;

    @Lob
    @Column(name = "models")
    private String models;

    @Column(name = "shippingprice")
    private Double shippingprice;

    @Column(name = "serialnumber")
    private String serialnumber;

    @Column(name = "tax")
    private Integer tax;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "price")
    private Double price;

    @Column(name = "maxqty")
    private Integer maxqty;

    @Column(name = "availableat")
    private Instant availableat;

    @Column(name = "lockactiondelay")
    private Instant lockactiondelay;

    @Column(name = "lockaction")
    private Boolean lockaction;

    @Column(name = "expireat")
    private Instant expireat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public Productdetails about(String about) {
        this.about = about;
        return this;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public Productdetails title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public Productdetails tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tagcolor getTagcolor() {
        return tagcolor;
    }

    public Productdetails tagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
        return this;
    }

    public void setTagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
    }

    public String getDefaultlanguage() {
        return defaultlanguage;
    }

    public Productdetails defaultlanguage(String defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
        return this;
    }

    public void setDefaultlanguage(String defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Productdetails postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhones() {
        return phones;
    }

    public Productdetails phones(String phones) {
        this.phones = phones;
        return this;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public Productdetails website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public Productdetails email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public Productdetails facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Productdetails twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGplus() {
        return gplus;
    }

    public Productdetails gplus(String gplus) {
        this.gplus = gplus;
        return this;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Productdetails linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public Productdetails instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getOpentimes() {
        return opentimes;
    }

    public Productdetails opentimes(String opentimes) {
        this.opentimes = opentimes;
        return this;
    }

    public void setOpentimes(String opentimes) {
        this.opentimes = opentimes;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public Productdetails othercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
        return this;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getOtherfields() {
        return otherfields;
    }

    public Productdetails otherfields(String otherfields) {
        this.otherfields = otherfields;
        return this;
    }

    public void setOtherfields(String otherfields) {
        this.otherfields = otherfields;
    }

    public String getSizes() {
        return sizes;
    }

    public Productdetails sizes(String sizes) {
        this.sizes = sizes;
        return this;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColors() {
        return colors;
    }

    public Productdetails colors(String colors) {
        this.colors = colors;
        return this;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getModels() {
        return models;
    }

    public Productdetails models(String models) {
        this.models = models;
        return this;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public Double getShippingprice() {
        return shippingprice;
    }

    public Productdetails shippingprice(Double shippingprice) {
        this.shippingprice = shippingprice;
        return this;
    }

    public void setShippingprice(Double shippingprice) {
        this.shippingprice = shippingprice;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public Productdetails serialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
        return this;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Integer getTax() {
        return tax;
    }

    public Productdetails tax(Integer tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getStock() {
        return stock;
    }

    public Productdetails stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public Productdetails price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxqty() {
        return maxqty;
    }

    public Productdetails maxqty(Integer maxqty) {
        this.maxqty = maxqty;
        return this;
    }

    public void setMaxqty(Integer maxqty) {
        this.maxqty = maxqty;
    }

    public Instant getAvailableat() {
        return availableat;
    }

    public Productdetails availableat(Instant availableat) {
        this.availableat = availableat;
        return this;
    }

    public void setAvailableat(Instant availableat) {
        this.availableat = availableat;
    }

    public Instant getLockactiondelay() {
        return lockactiondelay;
    }

    public Productdetails lockactiondelay(Instant lockactiondelay) {
        this.lockactiondelay = lockactiondelay;
        return this;
    }

    public void setLockactiondelay(Instant lockactiondelay) {
        this.lockactiondelay = lockactiondelay;
    }

    public Boolean isLockaction() {
        return lockaction;
    }

    public Productdetails lockaction(Boolean lockaction) {
        this.lockaction = lockaction;
        return this;
    }

    public void setLockaction(Boolean lockaction) {
        this.lockaction = lockaction;
    }

    public Instant getExpireat() {
        return expireat;
    }

    public Productdetails expireat(Instant expireat) {
        this.expireat = expireat;
        return this;
    }

    public void setExpireat(Instant expireat) {
        this.expireat = expireat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Productdetails)) {
            return false;
        }
        return id != null && id.equals(((Productdetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Productdetails{" +
            "id=" + getId() +
            ", about='" + getAbout() + "'" +
            ", title='" + getTitle() + "'" +
            ", tag='" + getTag() + "'" +
            ", tagcolor='" + getTagcolor() + "'" +
            ", defaultlanguage='" + getDefaultlanguage() + "'" +
            ", postalcode='" + getPostalcode() + "'" +
            ", phones='" + getPhones() + "'" +
            ", website='" + getWebsite() + "'" +
            ", email='" + getEmail() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", gplus='" + getGplus() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", opentimes='" + getOpentimes() + "'" +
            ", othercontacts='" + getOthercontacts() + "'" +
            ", otherfields='" + getOtherfields() + "'" +
            ", sizes='" + getSizes() + "'" +
            ", colors='" + getColors() + "'" +
            ", models='" + getModels() + "'" +
            ", shippingprice=" + getShippingprice() +
            ", serialnumber='" + getSerialnumber() + "'" +
            ", tax=" + getTax() +
            ", stock=" + getStock() +
            ", price=" + getPrice() +
            ", maxqty=" + getMaxqty() +
            ", availableat='" + getAvailableat() + "'" +
            ", lockactiondelay='" + getLockactiondelay() + "'" +
            ", lockaction='" + isLockaction() + "'" +
            ", expireat='" + getExpireat() + "'" +
            "}";
    }
}
