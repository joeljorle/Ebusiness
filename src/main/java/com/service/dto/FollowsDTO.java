package com.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Follows} entity.
 */
public class FollowsDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    @NotNull
    private Integer userid;

    private Integer categoryid;

    private Integer productid;

    private Integer tourid;

    private Integer evenementid;

    private Integer tourgroupid;

    private Boolean alert;

    private Boolean alertEvenement;

    private Boolean followchild;

    private Instant createdat;

    private Instant updatedat;


    private Long categoryId;

    private Long productId;

    private Long evenementId;

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

    public Integer getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Boolean isAlert() {
        return alert;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    public Boolean isAlertEvenement() {
        return alertEvenement;
    }

    public void setAlertEvenement(Boolean alertEvenement) {
        this.alertEvenement = alertEvenement;
    }

    public Boolean isFollowchild() {
        return followchild;
    }

    public void setFollowchild(Boolean followchild) {
        this.followchild = followchild;
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

    public Long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FollowsDTO followsDTO = (FollowsDTO) o;
        if (followsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), followsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FollowsDTO{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", userid=" + getUserid() +
            ", categoryid=" + getCategoryid() +
            ", productid=" + getProductid() +
            ", tourid=" + getTourid() +
            ", evenementid=" + getEvenementid() +
            ", tourgroupid=" + getTourgroupid() +
            ", alert='" + isAlert() + "'" +
            ", alertEvenement='" + isAlertEvenement() + "'" +
            ", followchild='" + isFollowchild() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", categoryId=" + getCategoryId() +
            ", productId=" + getProductId() +
            ", evenementId=" + getEvenementId() +
            "}";
    }
}
