package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Paymentmethod.
 */
@Entity
@Table(name = "paymentmethod")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paymentmethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name")
    private String name;

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

    /**
     * Payments to many relationship.
     */
    @OneToMany(mappedBy = "paymentmethod")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Paymentaction> paymentactions = new HashSet<>();

    @OneToMany(mappedBy = "paymentmethod")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Paymentcategory> paymentcategories = new HashSet<>();

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

    public Paymentmethod slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public Paymentmethod name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public Paymentmethod logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte[] getLogodata() {
        return logodata;
    }

    public Paymentmethod logodata(byte[] logodata) {
        this.logodata = logodata;
        return this;
    }

    public void setLogodata(byte[] logodata) {
        this.logodata = logodata;
    }

    public String getLogodataContentType() {
        return logodataContentType;
    }

    public Paymentmethod logodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
        return this;
    }

    public void setLogodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Paymentmethod createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Paymentmethod updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Set<Paymentaction> getPaymentactions() {
        return paymentactions;
    }

    public Paymentmethod paymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
        return this;
    }

    public Paymentmethod addPaymentaction(Paymentaction paymentaction) {
        this.paymentactions.add(paymentaction);
        paymentaction.setPaymentmethod(this);
        return this;
    }

    public Paymentmethod removePaymentaction(Paymentaction paymentaction) {
        this.paymentactions.remove(paymentaction);
        paymentaction.setPaymentmethod(null);
        return this;
    }

    public void setPaymentactions(Set<Paymentaction> paymentactions) {
        this.paymentactions = paymentactions;
    }

    public Set<Paymentcategory> getPaymentcategories() {
        return paymentcategories;
    }

    public Paymentmethod paymentcategories(Set<Paymentcategory> paymentcategories) {
        this.paymentcategories = paymentcategories;
        return this;
    }

    public Paymentmethod addPaymentcategory(Paymentcategory paymentcategory) {
        this.paymentcategories.add(paymentcategory);
        paymentcategory.setPaymentmethod(this);
        return this;
    }

    public Paymentmethod removePaymentcategory(Paymentcategory paymentcategory) {
        this.paymentcategories.remove(paymentcategory);
        paymentcategory.setPaymentmethod(null);
        return this;
    }

    public void setPaymentcategories(Set<Paymentcategory> paymentcategories) {
        this.paymentcategories = paymentcategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paymentmethod)) {
            return false;
        }
        return id != null && id.equals(((Paymentmethod) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paymentmethod{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", logodataContentType='" + getLogodataContentType() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
