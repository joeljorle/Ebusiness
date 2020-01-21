package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.domain.enumeration.Tagcolor;

/**
 * A Tourgroup.
 */
@Entity
@Table(name = "tourgroup")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tourgroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "evenementid")
    private Integer evenementid;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "islock")
    private Boolean islock;

    @Column(name = "lockdelay")
    private Instant lockdelay;

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

    @Column(name = "postalcode")
    private String postalcode;

    @Column(name = "phones")
    private String phones;

    @Column(name = "website")
    private String website;

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

    @Column(name = "email")
    private String email;

    @Column(name = "position")
    private Integer position;

    @Lob
    @Column(name = "othercontacts")
    private String othercontacts;

    @Lob
    @Column(name = "otherfields")
    private String otherfields;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @OneToOne
    @JoinColumn(unique = true)
    private Files files;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    @ManyToOne
    @JsonIgnoreProperties("tourgroups")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("tourgroups")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("tourgroups")
    private Evenement evenement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Tourgroup categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Tourgroup productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public Tourgroup evenementid(Integer evenementid) {
        this.evenementid = evenementid;
        return this;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public String getSlug() {
        return slug;
    }

    public Tourgroup slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public Tourgroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIslock() {
        return islock;
    }

    public Tourgroup islock(Boolean islock) {
        this.islock = islock;
        return this;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Instant getLockdelay() {
        return lockdelay;
    }

    public Tourgroup lockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
        return this;
    }

    public void setLockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
    }

    public String getAbout() {
        return about;
    }

    public Tourgroup about(String about) {
        this.about = about;
        return this;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public Tourgroup title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public Tourgroup tag(String tag) {
        this.tag = tag;
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Tagcolor getTagcolor() {
        return tagcolor;
    }

    public Tourgroup tagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
        return this;
    }

    public void setTagcolor(Tagcolor tagcolor) {
        this.tagcolor = tagcolor;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Tourgroup postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhones() {
        return phones;
    }

    public Tourgroup phones(String phones) {
        this.phones = phones;
        return this;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getWebsite() {
        return website;
    }

    public Tourgroup website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public Tourgroup facebook(String facebook) {
        this.facebook = facebook;
        return this;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public Tourgroup twitter(String twitter) {
        this.twitter = twitter;
        return this;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGplus() {
        return gplus;
    }

    public Tourgroup gplus(String gplus) {
        this.gplus = gplus;
        return this;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Tourgroup linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getInstagram() {
        return instagram;
    }

    public Tourgroup instagram(String instagram) {
        this.instagram = instagram;
        return this;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getEmail() {
        return email;
    }

    public Tourgroup email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPosition() {
        return position;
    }

    public Tourgroup position(Integer position) {
        this.position = position;
        return this;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getOthercontacts() {
        return othercontacts;
    }

    public Tourgroup othercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
        return this;
    }

    public void setOthercontacts(String othercontacts) {
        this.othercontacts = othercontacts;
    }

    public String getOtherfields() {
        return otherfields;
    }

    public Tourgroup otherfields(String otherfields) {
        this.otherfields = otherfields;
        return this;
    }

    public void setOtherfields(String otherfields) {
        this.otherfields = otherfields;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Tourgroup createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Tourgroup updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Files getFiles() {
        return files;
    }

    public Tourgroup files(Files files) {
        this.files = files;
        return this;
    }

    public void setFiles(Files files) {
        this.files = files;
    }

    public Location getLocation() {
        return location;
    }

    public Tourgroup location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public Tourgroup category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Tourgroup product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Tourgroup evenement(Evenement evenement) {
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
        if (!(o instanceof Tourgroup)) {
            return false;
        }
        return id != null && id.equals(((Tourgroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tourgroup{" +
            "id=" + getId() +
            ", categoryid=" + getCategoryid() +
            ", productid=" + getProductid() +
            ", evenementid=" + getEvenementid() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", islock='" + isIslock() + "'" +
            ", lockdelay='" + getLockdelay() + "'" +
            ", about='" + getAbout() + "'" +
            ", title='" + getTitle() + "'" +
            ", tag='" + getTag() + "'" +
            ", tagcolor='" + getTagcolor() + "'" +
            ", postalcode='" + getPostalcode() + "'" +
            ", phones='" + getPhones() + "'" +
            ", website='" + getWebsite() + "'" +
            ", facebook='" + getFacebook() + "'" +
            ", twitter='" + getTwitter() + "'" +
            ", gplus='" + getGplus() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", instagram='" + getInstagram() + "'" +
            ", email='" + getEmail() + "'" +
            ", position=" + getPosition() +
            ", othercontacts='" + getOthercontacts() + "'" +
            ", otherfields='" + getOtherfields() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
