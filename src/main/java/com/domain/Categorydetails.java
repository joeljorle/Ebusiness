package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.domain.enumeration.Tagcolor;

/**
 * A Categorydetails.
 */
@Entity
@Table(name = "categorydetails")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Categorydetails implements Serializable {

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

    @Column(name = "generalabout")
    private Boolean generalabout;

    @Column(name = "generaltax")
    private Integer generaltax;

    @Column(name = "generalstock")
    private Integer generalstock;

    @Column(name = "generalprice")
    private Double generalprice;

    @Column(name = "generalmaxstock")
    private Integer generalmaxstock;

    @Column(name = "generaltag")
    private String generaltag;

    @Enumerated(EnumType.STRING)
    @Column(name = "generaltagcolor")
    private Tagcolor generaltagcolor;

    @Column(name = "generalhidden")
    private Boolean generalhidden;

    @Column(name = "generalhiddendelay")
    private Instant generalhiddendelay;

    @Column(name = "generallock")
    private Boolean generallock;

    @Column(name = "generallockdelay")
    private Instant generallockdelay;

    @Column(name = "generalexpire")
    private Boolean generalexpire;

    @Column(name = "generalexpiredelay")
    private Instant generalexpiredelay;

    @Column(name = "childsname")
    private String childsname;

    @Column(name = "productsname")
    private String productsname;

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

    public Categorydetails about(String about) {
        this.about = about;
        return this;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public Categorydetails title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public Categorydetails tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tagcolor getTagcolor() {
        return tagcolor;
    }

    public Categorydetails tagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
        return this;
    }

    public void setTagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
    }

    public String getDefaultlanguage() {
        return defaultlanguage;
    }

    public Categorydetails defaultlanguage(String defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
        return this;
    }

    public void setDefaultlanguage(String defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Categorydetails postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhones() {
        return phones;
    }

    public Categorydetails phones(String phones) {
        this.phones = phones;
        return this;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public Categorydetails website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public Categorydetails email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public Categorydetails facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Categorydetails twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGplus() {
        return gplus;
    }

    public Categorydetails gplus(String gplus) {
        this.gplus = gplus;
        return this;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Categorydetails linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public Categorydetails instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getOpentimes() {
        return opentimes;
    }

    public Categorydetails opentimes(String opentimes) {
        this.opentimes = opentimes;
        return this;
    }

    public void setOpentimes(String opentimes) {
        this.opentimes = opentimes;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public Categorydetails othercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
        return this;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getOtherfields() {
        return otherfields;
    }

    public Categorydetails otherfields(String otherfields) {
        this.otherfields = otherfields;
        return this;
    }

    public void setOtherfields(String otherfields) {
        this.otherfields = otherfields;
    }

    public Boolean isGeneralabout() {
        return generalabout;
    }

    public Categorydetails generalabout(Boolean generalabout) {
        this.generalabout = generalabout;
        return this;
    }

    public void setGeneralabout(Boolean generalabout) {
        this.generalabout = generalabout;
    }

    public Integer getGeneraltax() {
        return generaltax;
    }

    public Categorydetails generaltax(Integer generaltax) {
        this.generaltax = generaltax;
        return this;
    }

    public void setGeneraltax(Integer generaltax) {
        this.generaltax = generaltax;
    }

    public Integer getGeneralstock() {
        return generalstock;
    }

    public Categorydetails generalstock(Integer generalstock) {
        this.generalstock = generalstock;
        return this;
    }

    public void setGeneralstock(Integer generalstock) {
        this.generalstock = generalstock;
    }

    public Double getGeneralprice() {
        return generalprice;
    }

    public Categorydetails generalprice(Double generalprice) {
        this.generalprice = generalprice;
        return this;
    }

    public void setGeneralprice(Double generalprice) {
        this.generalprice = generalprice;
    }

    public Integer getGeneralmaxstock() {
        return generalmaxstock;
    }

    public Categorydetails generalmaxstock(Integer generalmaxstock) {
        this.generalmaxstock = generalmaxstock;
        return this;
    }

    public void setGeneralmaxstock(Integer generalmaxstock) {
        this.generalmaxstock = generalmaxstock;
    }

    public String getGeneraltag() {
        return generaltag;
    }

    public Categorydetails generaltag(String generaltag) {
        this.generaltag = generaltag;
        return this;
    }

    public void setGeneraltag(String generaltag) {
        this.generaltag = generaltag;
    }

    public Tagcolor getGeneraltagcolor() {
        return generaltagcolor;
    }

    public Categorydetails generaltagcolor(Tagcolor generaltagcolor) {
        this.generaltagcolor = generaltagcolor;
        return this;
    }

    public void setGeneraltagcolor(Tagcolor generaltagcolor) {
        this.generaltagcolor = generaltagcolor;
    }

    public Boolean isGeneralhidden() {
        return generalhidden;
    }

    public Categorydetails generalhidden(Boolean generalhidden) {
        this.generalhidden = generalhidden;
        return this;
    }

    public void setGeneralhidden(Boolean generalhidden) {
        this.generalhidden = generalhidden;
    }

    public Instant getGeneralhiddendelay() {
        return generalhiddendelay;
    }

    public Categorydetails generalhiddendelay(Instant generalhiddendelay) {
        this.generalhiddendelay = generalhiddendelay;
        return this;
    }

    public void setGeneralhiddendelay(Instant generalhiddendelay) {
        this.generalhiddendelay = generalhiddendelay;
    }

    public Boolean isGenerallock() {
        return generallock;
    }

    public Categorydetails generallock(Boolean generallock) {
        this.generallock = generallock;
        return this;
    }

    public void setGenerallock(Boolean generallock) {
        this.generallock = generallock;
    }

    public Instant getGenerallockdelay() {
        return generallockdelay;
    }

    public Categorydetails generallockdelay(Instant generallockdelay) {
        this.generallockdelay = generallockdelay;
        return this;
    }

    public void setGenerallockdelay(Instant generallockdelay) {
        this.generallockdelay = generallockdelay;
    }

    public Boolean isGeneralexpire() {
        return generalexpire;
    }

    public Categorydetails generalexpire(Boolean generalexpire) {
        this.generalexpire = generalexpire;
        return this;
    }

    public void setGeneralexpire(Boolean generalexpire) {
        this.generalexpire = generalexpire;
    }

    public Instant getGeneralexpiredelay() {
        return generalexpiredelay;
    }

    public Categorydetails generalexpiredelay(Instant generalexpiredelay) {
        this.generalexpiredelay = generalexpiredelay;
        return this;
    }

    public void setGeneralexpiredelay(Instant generalexpiredelay) {
        this.generalexpiredelay = generalexpiredelay;
    }

    public String getChildsname() {
        return childsname;
    }

    public Categorydetails childsname(String childsname) {
        this.childsname = childsname;
        return this;
    }

    public void setChildsname(String childsname) {
        this.childsname = childsname;
    }

    public String getProductsname() {
        return productsname;
    }

    public Categorydetails productsname(String productsname) {
        this.productsname = productsname;
        return this;
    }

    public void setProductsname(String productsname) {
        this.productsname = productsname;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorydetails)) {
            return false;
        }
        return id != null && id.equals(((Categorydetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Categorydetails{" +
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
