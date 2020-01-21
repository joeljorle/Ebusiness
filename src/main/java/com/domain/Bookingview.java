package com.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Bookingview.
 */
@Entity
@Table(name = "bookingview")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bookingview implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "logo")
    private String logo;

    @Lob
    @Column(name = "logodata")
    private byte[] logodata;

    @Column(name = "logodata_content_type")
    private String logodataContentType;

    @Column(name = "bg")
    private String bg;

    @Lob
    @Column(name = "bgdata")
    private byte[] bgdata;

    @Column(name = "bgdata_content_type")
    private String bgdataContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Bookingview title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Bookingview subtitle(String subtitle) {
        this.subtitle = subtitle;
        return this;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getLogo() {
        return logo;
    }

    public Bookingview logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte[] getLogodata() {
        return logodata;
    }

    public Bookingview logodata(byte[] logodata) {
        this.logodata = logodata;
        return this;
    }

    public void setLogodata(byte[] logodata) {
        this.logodata = logodata;
    }

    public String getLogodataContentType() {
        return logodataContentType;
    }

    public Bookingview logodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
        return this;
    }

    public void setLogodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
    }

    public String getBg() {
        return bg;
    }

    public Bookingview bg(String bg) {
        this.bg = bg;
        return this;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public byte[] getBgdata() {
        return bgdata;
    }

    public Bookingview bgdata(byte[] bgdata) {
        this.bgdata = bgdata;
        return this;
    }

    public void setBgdata(byte[] bgdata) {
        this.bgdata = bgdata;
    }

    public String getBgdataContentType() {
        return bgdataContentType;
    }

    public Bookingview bgdataContentType(String bgdataContentType) {
        this.bgdataContentType = bgdataContentType;
        return this;
    }

    public void setBgdataContentType(String bgdataContentType) {
        this.bgdataContentType = bgdataContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bookingview)) {
            return false;
        }
        return id != null && id.equals(((Bookingview) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bookingview{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", subtitle='" + getSubtitle() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", logodataContentType='" + getLogodataContentType() + "'" +
            ", bg='" + getBg() + "'" +
            ", bgdata='" + getBgdata() + "'" +
            ", bgdataContentType='" + getBgdataContentType() + "'" +
            "}";
    }
}
