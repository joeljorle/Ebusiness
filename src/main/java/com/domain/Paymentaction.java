package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Paymentaction.
 */
@Entity
@Table(name = "paymentaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paymentaction implements Serializable {

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

    @Column(name = "paymentmethodid")
    private Integer paymentmethodid;

    @Column(name = "evenementid")
    private Integer evenementid;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "expireat")
    private Instant expireat;

    @Column(name = "code")
    private String code;

    @Column(name = "code_1")
    private String code1;

    @Column(name = "code_2")
    private String code2;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @ManyToOne
    @JsonIgnoreProperties("paymentactions")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("paymentactions")
    private Evenement evenement;

    @ManyToOne
    @JsonIgnoreProperties("paymentactions")
    private Paymentmethod paymentmethod;

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

    public Paymentaction slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Paymentaction userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPaymentmethodid() {
        return paymentmethodid;
    }

    public Paymentaction paymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
        return this;
    }

    public void setPaymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public Paymentaction evenementid(Integer evenementid) {
        this.evenementid = evenementid;
        return this;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Paymentaction productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Paymentaction categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Instant getExpireat() {
        return expireat;
    }

    public Paymentaction expireat(Instant expireat) {
        this.expireat = expireat;
        return this;
    }

    public void setExpireat(Instant expireat) {
        this.expireat = expireat;
    }

    public String getCode() {
        return code;
    }

    public Paymentaction code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode1() {
        return code1;
    }

    public Paymentaction code1(String code1) {
        this.code1 = code1;
        return this;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public Paymentaction code2(String code2) {
        this.code2 = code2;
        return this;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public Double getAmount() {
        return amount;
    }

    public Paymentaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Paymentaction createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Paymentaction updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Product getProduct() {
        return product;
    }

    public Paymentaction product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Paymentaction evenement(Evenement evenement) {
        this.evenement = evenement;
        return this;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Paymentmethod getPaymentmethod() {
        return paymentmethod;
    }

    public Paymentaction paymentmethod(Paymentmethod paymentmethod) {
        this.paymentmethod = paymentmethod;
        return this;
    }

    public void setPaymentmethod(Paymentmethod paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paymentaction)) {
            return false;
        }
        return id != null && id.equals(((Paymentaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paymentaction{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", paymentmethodid=" + getPaymentmethodid() +
            ", evenementid=" + getEvenementid() +
            ", productid=" + getProductid() +
            ", categoryid=" + getCategoryid() +
            ", expireat='" + getExpireat() + "'" +
            ", code='" + getCode() + "'" +
            ", code1='" + getCode1() + "'" +
            ", code2='" + getCode2() + "'" +
            ", amount=" + getAmount() +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
