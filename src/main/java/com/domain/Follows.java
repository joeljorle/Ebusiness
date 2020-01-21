package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Follows.
 */
@Entity
@Table(name = "follows")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Follows implements Serializable {

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

    @Column(name = "categoryid")
    private Integer categoryid;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "tourid")
    private Integer tourid;

    @Column(name = "evenementid")
    private Integer evenementid;

    @Column(name = "tourgroupid")
    private Integer tourgroupid;

    @Column(name = "alert")
    private Boolean alert;

    @Column(name = "alert_evenement")
    private Boolean alertEvenement;

    @Column(name = "followchild")
    private Boolean followchild;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @ManyToOne
    @JsonIgnoreProperties("follows")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("follows")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("follows")
    private Evenement evenement;

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

    public Follows slug(String slug) {
        this.slug = slug;
        return this;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getUserid() {
        return userid;
    }

    public Follows userid(Integer userid) {
        this.userid = userid;
        return this;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Follows categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProductid() {
        return productid;
    }

    public Follows productid(Integer productid) {
        this.productid = productid;
        return this;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getTourid() {
        return tourid;
    }

    public Follows tourid(Integer tourid) {
        this.tourid = tourid;
        return this;
    }

    public void setTourid(Integer tourid) {
        this.tourid = tourid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public Follows evenementid(Integer evenementid) {
        this.evenementid = evenementid;
        return this;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Integer getTourgroupid() {
        return tourgroupid;
    }

    public Follows tourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
        return this;
    }

    public void setTourgroupid(Integer tourgroupid) {
        this.tourgroupid = tourgroupid;
    }

    public Boolean isAlert() {
        return alert;
    }

    public Follows alert(Boolean alert) {
        this.alert = alert;
        return this;
    }

    public void setAlert(Boolean alert) {
        this.alert = alert;
    }

    public Boolean isAlertEvenement() {
        return alertEvenement;
    }

    public Follows alertEvenement(Boolean alertEvenement) {
        this.alertEvenement = alertEvenement;
        return this;
    }

    public void setAlertEvenement(Boolean alertEvenement) {
        this.alertEvenement = alertEvenement;
    }

    public Boolean isFollowchild() {
        return followchild;
    }

    public Follows followchild(Boolean followchild) {
        this.followchild = followchild;
        return this;
    }

    public void setFollowchild(Boolean followchild) {
        this.followchild = followchild;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Follows createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Follows updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Category getCategory() {
        return category;
    }

    public Follows category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public Follows product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public Follows evenement(Evenement evenement) {
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
        if (!(o instanceof Follows)) {
            return false;
        }
        return id != null && id.equals(((Follows) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Follows{" +
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
            "}";
    }
}
