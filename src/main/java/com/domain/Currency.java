package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name")
    private String name;

    @Column(name = "abrev")
    private String abrev;

    @Lob
    @Column(name = "logo")
    private String logo;

    @Lob
    @Column(name = "logodata")
    private byte[] logodata;

    @Column(name = "logodata_content_type")
    private String logodataContentType;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @ManyToOne
    @JsonIgnoreProperties("currencies")
    private Category category;

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

    public Currency slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public Currency name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbrev() {
        return abrev;
    }

    public Currency abrev(String abrev) {
        this.abrev = abrev;
        return this;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
    }

    public String getLogo() {
        return logo;
    }

    public Currency logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte[] getLogodata() {
        return logodata;
    }

    public Currency logodata(byte[] logodata) {
        this.logodata = logodata;
        return this;
    }

    public void setLogodata(byte[] logodata) {
        this.logodata = logodata;
    }

    public String getLogodataContentType() {
        return logodataContentType;
    }

    public Currency logodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
        return this;
    }

    public void setLogodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Currency createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Currency updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Category getCategory() {
        return category;
    }

    public Currency category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        return id != null && id.equals(((Currency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", abrev='" + getAbrev() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", logodataContentType='" + getLogodataContentType() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
