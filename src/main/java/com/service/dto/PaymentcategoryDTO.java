package com.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Paymentcategory} entity.
 */
public class PaymentcategoryDTO implements Serializable {

    private Long id;

    private Integer paymentmethodid;

    @NotNull
    private Integer categoryid;

    private String url;

    private String apiurl;

    private String apikey;

    private String key2;

    private String key3;

    private String key4;

    private String phonenumber;

    private String chanel;

    private String code;

    private String username;

    private String password;

    private Instant createdat;

    private Instant updatedat;


    private Long categoryId;

    private Long paymentmethodId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentmethodid() {
        return paymentmethodid;
    }

    public void setPaymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public String getKey4() {
        return key4;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getChanel() {
        return chanel;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getPaymentmethodId() {
        return paymentmethodId;
    }

    public void setPaymentmethodId(Long paymentmethodId) {
        this.paymentmethodId = paymentmethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PaymentcategoryDTO paymentcategoryDTO = (PaymentcategoryDTO) o;
        if (paymentcategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentcategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentcategoryDTO{" +
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
            ", categoryId=" + getCategoryId() +
            ", paymentmethodId=" + getPaymentmethodId() +
            "}";
    }
}
