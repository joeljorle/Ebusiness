package com.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Files} entity.
 */
public class FilesDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    private Integer userid;

    private Integer categoryid;

    private Integer productid;

    private Integer tourid;

    private Integer tourgroupid;

    private Integer tourcategoryid;

    private Integer evenementid;

    private Boolean islogoimg;

    private Boolean isprofileimg;

    private Boolean iscoverimg;

    private Boolean issliderimg;

    private Boolean isotherimg;

    private Instant createdat;

    private Instant updatedat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getTourid() {
        return tourid;
    }

    public void setTourid(Integer tourid) {
        this.tourid = tourid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Integer getTourcategoryid() {
        return tourcategoryid;
    }

    public void setTourcategoryid(Integer tourcategoryid) {
        this.tourcategoryid = tourcategoryid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Boolean isIslogoimg() {
        return islogoimg;
    }

    public void setIslogoimg(Boolean islogoimg) {
        this.islogoimg = islogoimg;
    }

    public Boolean isIsprofileimg() {
        return isprofileimg;
    }

    public void setIsprofileimg(Boolean isprofileimg) {
        this.isprofileimg = isprofileimg;
    }

    public Boolean isIscoverimg() {
        return iscoverimg;
    }

    public void setIscoverimg(Boolean iscoverimg) {
        this.iscoverimg = iscoverimg;
    }

    public Boolean isIssliderimg() {
        return issliderimg;
    }

    public void setIssliderimg(Boolean issliderimg) {
        this.issliderimg = issliderimg;
    }

    public Boolean isIsotherimg() {
        return isotherimg;
    }

    public void setIsotherimg(Boolean isotherimg) {
        this.isotherimg = isotherimg;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilesDTO filesDTO = (FilesDTO) o;
        if (filesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilesDTO{" +
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
