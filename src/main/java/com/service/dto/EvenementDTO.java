package com.service.dto;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.domain.enumeration.Tagcolor;

/**
 * A DTO for the {@link com.domain.Evenement} entity.
 */
public class EvenementDTO implements Serializable {

    private Long id;

    private Integer categoryid;

    private Integer userid;

    private Integer productid;

    @NotNull
    private String slug;

    private String name;

    private Boolean islock;

    private Instant lockdelay;

    private Boolean islimited;

    private Instant limiteddelay;

    private Integer limitedbooking;

    private Instant startdate;

    private Instant enddate;

    @Lob
    private String about;

    private String title;

    private String tag;

    private Tagcolor tagcolor;

    private String postalcode;

    private String phones;

    private String website;

    private String facebook;

    private String twitter;

    private String gplus;

    private String linkedin;

    private String instagram;

    private String email;

    private String url;

    @Lob
    private String othercontacts;

    @Lob
    private String otherfields;

    private Instant createdat;

    private Instant updatedat;


    private Long filesId;

    private Long currencyId;

    private Long locationId;
    /**
     * Evenement to many relationship.
     */
    @ApiModelProperty(value = "Evenement to many relationship.")

    private Long categoryId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIslock() {
        return islock;
    }

    public void setIslock(Boolean islock) {
        this.islock = islock;
    }

    public Instant getLockdelay() {
        return lockdelay;
    }

    public void setLockdelay(Instant lockdelay) {
        this.lockdelay = lockdelay;
    }

    public Boolean isIslimited() {
        return islimited;
    }

    public void setIslimited(Boolean islimited) {
        this.islimited = islimited;
    }

    public Instant getLimiteddelay() {
        return limiteddelay;
    }

    public void setLimiteddelay(Instant limiteddelay) {
        this.limiteddelay = limiteddelay;
    }

    public Integer getLimitedbooking() {
        return limitedbooking;
    }

    public void setLimitedbooking(Integer limitedbooking) {
        this.limitedbooking = limitedbooking;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Long getFilesId() {
        return filesId;
    }

    public void setFilesId(Long filesId) {
        this.filesId = filesId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvenementDTO evenementDTO = (EvenementDTO) o;
        if (evenementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evenementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvenementDTO{" +
            "id=" + getId() +
            ", categoryid=" + getCategoryid() +
            ", userid=" + getUserid() +
            ", productid=" + getProductid() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", islock='" + isIslock() + "'" +
            ", lockdelay='" + getLockdelay() + "'" +
            ", islimited='" + isIslimited() + "'" +
            ", limiteddelay='" + getLimiteddelay() + "'" +
            ", limitedbooking=" + getLimitedbooking() +
            ", startdate='" + getStartdate() + "'" +
            ", enddate='" + getEnddate() + "'" +
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
            ", url='" + getUrl() + "'" +
            ", othercontacts='" + getOthercontacts() + "'" +
            ", otherfields='" + getOtherfields() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", filesId=" + getFilesId() +
            ", currencyId=" + getCurrencyId() +
            ", locationId=" + getLocationId() +
            ", categoryId=" + getCategoryId() +
            ", productId=" + getProductId() +
            "}";
    }
}
