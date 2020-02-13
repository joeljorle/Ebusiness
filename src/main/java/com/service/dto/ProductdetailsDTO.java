package com.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.domain.enumeration.Tagcolor;

/**
 * A DTO for the {@link com.domain.Productdetails} entity.
 */
public class ProductdetailsDTO implements Serializable {

    private Long id;

    @Lob
    private String about;

    private String title;

    private String tag;

    private Tagcolor tagcolor;

    private String defaultlanguage;

    private String postalcode;

    private String phones;

    private String website;

    private String email;

    private String facebook;

    private String twitter;

    private String gplus;

    private String linkedin;

    private String instagram;

    @Lob
    private String opentimes;

    @Lob
    private String othercontacts;

    @Lob
    private String otherfields;

    @Lob
    private String sizes;

    @Lob
    private String colors;

    @Lob
    private String models;

    private Double shippingprice;

    private String serialnumber;

    private Integer tax;

    private Integer stock;

    private Double price;

    private Integer maxqty;

    private Instant availableat;

    private Instant lockactiondelay;

    private Boolean lockaction;

    private Instant expireat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tagcolor getTagcolor() {
        return tagcolor;
    }

    public void setTagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
    }

    public String getDefaultlanguage() {
        return defaultlanguage;
    }

    public void setDefaultlanguage(String defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGplus() {
        return gplus;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getOpentimes() {
        return opentimes;
    }

    public void setOpentimes(String opentimes) {
        this.opentimes = opentimes;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getOtherfields() {
        return otherfields;
    }

    public void setOtherfields(String otherfields) {
        this.otherfields = otherfields;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public Double getShippingprice() {
        return shippingprice;
    }

    public void setShippingprice(Double shippingprice) {
        this.shippingprice = shippingprice;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMaxqty() {
        return maxqty;
    }

    public void setMaxqty(Integer maxqty) {
        this.maxqty = maxqty;
    }

    public Instant getAvailableat() {
        return availableat;
    }

    public void setAvailableat(Instant availableat) {
        this.availableat = availableat;
    }

    public Instant getLockactiondelay() {
        return lockactiondelay;
    }

    public void setLockactiondelay(Instant lockactiondelay) {
        this.lockactiondelay = lockactiondelay;
    }

    public Boolean isLockaction() {
        return lockaction;
    }

    public void setLockaction(Boolean lockaction) {
        this.lockaction = lockaction;
    }

    public Instant getExpireat() {
        return expireat;
    }

    public void setExpireat(Instant expireat) {
        this.expireat = expireat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductdetailsDTO productdetailsDTO = (ProductdetailsDTO) o;
        if (productdetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productdetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductdetailsDTO{" +
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
