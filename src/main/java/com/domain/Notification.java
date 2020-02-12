package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.domain.enumeration.Notificationmodel;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @NotNull
    @Column(name = "userid", nullable = false)
    private Integer userid;

    @Column(name = "urltarget")
    private String urltarget;

    @Column(name = "message")
    private String message;

    @Column(name = "title")
    private String title;

    @Column(name = "logo")
    private String logo;

    @Lob
    @Column(name = "logodata")
    private byte[] logodata;

    @Column(name = "logodata_content_type")
    private String logodataContentType;

    @Column(name = "isview")
    private Boolean isview;

    @Enumerated(EnumType.STRING)
    @Column(name = "notificationmodel")
    private Notificationmodel notificationmodel;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public Notification slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Notification userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUrltarget() {
        return urltarget;
    }

    public Notification urltarget(String urltarget) {
        this.urltarget = urltarget;
        return this;
    }

    public void setUrltarget(String urltarget) {
        this.urltarget = urltarget;
    }

    public String getMessage() {
        return message;
    }

    public Notification message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public Notification title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public Notification logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte[] getLogodata() {
        return logodata;
    }

    public Notification logodata(byte[] logodata) {
        this.logodata = logodata;
        return this;
    }

    public void setLogodata(byte[] logodata) {
        this.logodata = logodata;
    }

    public String getLogodataContentType() {
        return logodataContentType;
    }

    public Notification logodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
        return this;
    }

    public void setLogodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
    }

    public Boolean isIsview() {
        return isview;
    }

    public Notification isview(Boolean isview) {
        this.isview = isview;
        return this;
    }

    public void setIsview(Boolean isview) {
        this.isview = isview;
    }

    public Notificationmodel getNotificationmodel() {
        return notificationmodel;
    }

    public Notification notificationmodel(Notificationmodel notificationmodel) {
        this.notificationmodel = notificationmodel;
        return this;
    }

    public void setNotificationmodel(Notificationmodel notificationmodel) {
        this.notificationmodel = notificationmodel;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Notification createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Notification updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Notification)) {
            return false;
        }
        return id != null && id.equals(((Notification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", urltarget='" + getUrltarget() + "'" +
            ", message='" + getMessage() + "'" +
            ", title='" + getTitle() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", logodataContentType='" + getLogodataContentType() + "'" +
            ", isview='" + isIsview() + "'" +
            ", notificationmodel='" + getNotificationmodel() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
