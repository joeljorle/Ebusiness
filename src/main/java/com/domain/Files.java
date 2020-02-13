package com.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Files.
 */
@Entity
@Table(name = "files")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "userid")
    private Integer userid;

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "tourid")
    private Integer tourid;

    @Column(name = "tourgroupid")
    private Integer tourgroupid;

    @Column(name = "tourcategoryid")
    private Integer tourcategoryid;

    @Column(name = "evenementid")
    private Integer evenementid;

    @Column(name = "islogoimg")
    private Boolean islogoimg;

    @Column(name = "isprofileimg")
    private Boolean isprofileimg;

    @Column(name = "iscoverimg")
    private Boolean iscoverimg;

    @Column(name = "issliderimg")
    private Boolean issliderimg;

    @Column(name = "isotherimg")
    private Boolean isotherimg;

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

    public Files slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Files userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Files categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Files productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getTourid() {
        return tourid;
    }

    public Files tourid(Integer tourid) {
        this.tourid = tourid;
        return this;
    }

    public void setTourid(Integer tourid) {
        this.tourid = tourid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public Files tourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
        return this;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Integer getTourcategoryid() {
        return tourcategoryid;
    }

    public Files tourcategoryid(Integer tourcategoryid) {
        this.tourcategoryid = tourcategoryid;
        return this;
    }

    public void setTourcategoryid(Integer tourcategoryid) {
        this.tourcategoryid = tourcategoryid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public Files evenementid(Integer evenementid) {
        this.evenementid = evenementid;
        return this;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Boolean isIslogoimg() {
        return islogoimg;
    }

    public Files islogoimg(Boolean islogoimg) {
        this.islogoimg = islogoimg;
        return this;
    }

    public void setIslogoimg(Boolean islogoimg) {
        this.islogoimg = islogoimg;
    }

    public Boolean isIsprofileimg() {
        return isprofileimg;
    }

    public Files isprofileimg(Boolean isprofileimg) {
        this.isprofileimg = isprofileimg;
        return this;
    }

    public void setIsprofileimg(Boolean isprofileimg) {
        this.isprofileimg = isprofileimg;
    }

    public Boolean isIscoverimg() {
        return iscoverimg;
    }

    public Files iscoverimg(Boolean iscoverimg) {
        this.iscoverimg = iscoverimg;
        return this;
    }

    public void setIscoverimg(Boolean iscoverimg) {
        this.iscoverimg = iscoverimg;
    }

    public Boolean isIssliderimg() {
        return issliderimg;
    }

    public Files issliderimg(Boolean issliderimg) {
        this.issliderimg = issliderimg;
        return this;
    }

    public void setIssliderimg(Boolean issliderimg) {
        this.issliderimg = issliderimg;
    }

    public Boolean isIsotherimg() {
        return isotherimg;
    }

    public Files isotherimg(Boolean isotherimg) {
        this.isotherimg = isotherimg;
        return this;
    }

    public void setIsotherimg(Boolean isotherimg) {
        this.isotherimg = isotherimg;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Files createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Files updatedat(Instant updatedat) {
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
        if (!(o instanceof Files)) {
            return false;
        }
        return id != null && id.equals(((Files) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Files{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", categoryid=" + getCategoryid() +
            ", productid=" + getProductid() +
            ", tourid=" + getTourid() +
            ", tourgroupid=" + getTourgroupid() +
            ", tourcategoryid=" + getTourcategoryid() +
            ", evenementid=" + getEvenementid() +
            ", islogoimg='" + isIslogoimg() + "'" +
            ", isprofileimg='" + isIsprofileimg() + "'" +
            ", iscoverimg='" + isIscoverimg() + "'" +
            ", issliderimg='" + isIssliderimg() + "'" +
            ", isotherimg='" + isIsotherimg() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
