package com.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.domain.enumeration.Tagcolor;

/**
 * A DTO for the {@link com.domain.Categorydetails} entity.
 */
public class CategorydetailsDTO implements Serializable {

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

    private Boolean generalabout;

    private Integer generaltax;

    private Integer generalstock;

    private Double generalprice;

    private Integer generalmaxstock;

    private String generaltag;

    private Tagcolor generaltagcolor;

    private Boolean generalhidden;

    private Instant generalhiddendelay;

    private Boolean generallock;

    private Instant generallockdelay;

    private Boolean generalexpire;

    private Instant generalexpiredelay;

    private String childsname;

    private String productsname;


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

    public Boolean isGeneralabout() {
        return generalabout;
    }

    public void setGeneralabout(Boolean generalabout) {
        this.generalabout = generalabout;
    }

    public Integer getGeneraltax() {
        return generaltax;
    }

    public void setGeneraltax(Integer generaltax) {
        this.generaltax = generaltax;
    }

    public Integer getGeneralstock() {
        return generalstock;
    }

    public void setGeneralstock(Integer generalstock) {
        this.generalstock = generalstock;
    }

    public Double getGeneralprice() {
        return generalprice;
    }

    public void setGeneralprice(Double generalprice) {
        this.generalprice = generalprice;
    }

    public Integer getGeneralmaxstock() {
        return generalmaxstock;
    }

    public void setGeneralmaxstock(Integer generalmaxstock) {
        this.generalmaxstock = generalmaxstock;
    }

    public String getGeneraltag() {
        return generaltag;
    }

    public void setGeneraltag(String generaltag) {
        this.generaltag = generaltag;
    }

    public Tagcolor getGeneraltagcolor() {
        return generaltagcolor;
    }

    public void setGeneraltagcolor(Tagcolor generaltagcolor) {
        this.generaltagcolor = generaltagcolor;
    }

    public Boolean isGeneralhidden() {
        return generalhidden;
    }

    public void setGeneralhidden(Boolean generalhidden) {
        this.generalhidden = generalhidden;
    }

    public Instant getGeneralhiddendelay() {
        return generalhiddendelay;
    }

    public void setGeneralhiddendelay(Instant generalhiddendelay) {
        this.generalhiddendelay = generalhiddendelay;
    }

    public Boolean isGenerallock() {
        return generallock;
    }

    public void setGenerallock(Boolean generallock) {
        this.generallock = generallock;
    }

    public Instant getGenerallockdelay() {
        return generallockdelay;
    }

    public void setGenerallockdelay(Instant generallockdelay) {
        this.generallockdelay = generallockdelay;
    }

    public Boolean isGeneralexpire() {
        return generalexpire;
    }

    public void setGeneralexpire(Boolean generalexpire) {
        this.generalexpire = generalexpire;
    }

    public Instant getGeneralexpiredelay() {
        return generalexpiredelay;
    }

    public void setGeneralexpiredelay(Instant generalexpiredelay) {
        this.generalexpiredelay = generalexpiredelay;
    }

    public String getChildsname() {
        return childsname;
    }

    public void setChildsname(String childsname) {
        this.childsname = childsname;
    }

    public String getProductsname() {
        return productsname;
    }

    public void setProductsname(String productsname) {
        this.productsname = productsname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategorydetailsDTO categorydetailsDTO = (CategorydetailsDTO) o;
        if (categorydetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), categorydetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CategorydetailsDTO{" +
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
            ", generalabout='" + isGeneralabout() + "'" +
            ", generaltax=" + getGeneraltax() +
            ", generalstock=" + getGeneralstock() +
            ", generalprice=" + getGeneralprice() +
            ", generalmaxstock=" + getGeneralmaxstock() +
            ", generaltag='" + getGeneraltag() + "'" +
            ", generaltagcolor='" + getGeneraltagcolor() + "'" +
            ", generalhidden='" + isGeneralhidden() + "'" +
            ", generalhiddendelay='" + getGeneralhiddendelay() + "'" +
            ", generallock='" + isGenerallock() + "'" +
            ", generallockdelay='" + getGenerallockdelay() + "'" +
            ", generalexpire='" + isGeneralexpire() + "'" +
            ", generalexpiredelay='" + getGeneralexpiredelay() + "'" +
            ", childsname='" + getChildsname() + "'" +
            ", productsname='" + getProductsname() + "'" +
            "}";
    }
}
