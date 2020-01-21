package com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Paymentcategory.
 */
@Entity
@Table(name = "paymentcategory")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paymentcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paymentmethodid")
    private Integer paymentmethodid;

    @NotNull
    @Column(name = "categoryid", nullable = false)
    private Integer categoryid;

    @Column(name = "url")
    private String url;

    @Column(name = "apiurl")
    private String apiurl;

    @Column(name = "apikey")
    private String apikey;

    @Column(name = "key_2")
    private String key2;

    @Column(name = "key_3")
    private String key3;

    @Column(name = "key_4")
    private String key4;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "chanel")
    private String chanel;

    @Column(name = "code")
    private String code;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "createdat")
    private Instant createdat;

    @Column(name = "updatedat")
    private Instant updatedat;

    @ManyToOne
    @JsonIgnoreProperties("paymentcategories")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("paymentcategories")
    private Paymentmethod paymentmethod;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentmethodid() {
        return paymentmethodid;
    }

    public Paymentcategory paymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
        return this;
    }

    public void setPaymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public Paymentcategory categoryid(Integer categoryid) {
        this.categoryid = categoryid;
        return this;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getUrl() {
        return url;
    }

    public Paymentcategory url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiurl() {
        return apiurl;
    }

    public Paymentcategory apiurl(String apiurl) {
        this.apiurl = apiurl;
        return this;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getApikey() {
        return apikey;
    }

    public Paymentcategory apikey(String apikey) {
        this.apikey = apikey;
        return this;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getKey2() {
        return key2;
    }

    public Paymentcategory key2(String key2) {
        this.key2 = key2;
        return this;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public Paymentcategory key3(String key3) {
        this.key3 = key3;
        return this;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return key4;
    }

    public Paymentcategory key4(String key4) {
        this.key4 = key4;
        return this;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Paymentcategory phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getChanel() {
        return chanel;
    }

    public Paymentcategory chanel(String chanel) {
        this.chanel = chanel;
        return this;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getCode() {
        return code;
    }

    public Paymentcategory code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public Paymentcategory username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Paymentcategory password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreatedat() {
        return createdat;
    }

    public Paymentcategory createdat(Instant createdat) {
        this.createdat = createdat;
        return this;
    }

    public void setCreatedat(Instant createdat) {
        this.createdat = createdat;
    }

    public Instant getUpdatedat() {
        return updatedat;
    }

    public Paymentcategory updatedat(Instant updatedat) {
        this.updatedat = updatedat;
        return this;
    }

    public void setUpdatedat(Instant updatedat) {
        this.updatedat = updatedat;
    }

    public Category getCategory() {
        return category;
    }

    public Paymentcategory category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Paymentmethod getPaymentmethod() {
        return paymentmethod;
    }

    public Paymentcategory paymentmethod(Paymentmethod paymentmethod) {
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
        if (!(o instanceof Paymentcategory)) {
            return false;
        }
        return id != null && id.equals(((Paymentcategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paymentcategory{" +
            "id=" + getId() +
            ", paymentmethodid=" + getPaymentmethodid() +
            ", categoryid=" + getCategoryid() +
            ", url='" + getUrl() + "'" +
            ", apiurl='" + getApiurl() + "'" +
            ", apikey='" + getApikey() + "'" +
            ", key2='" + getKey2() + "'" +
            ", key3='" + getKey3() + "'" +
            ", key4='" + getKey4() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", chanel='" + getChanel() + "'" +
            ", code='" + getCode() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
