package com.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.domain.Paymentaction} entity.
 */
public class PaymentactionDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    @NotNull
    private Integer userid;

    private Integer paymentmethodid;

    private Integer evenementid;

    private Integer productid;

    private Integer categoryid;

    private Instant expireat;

    private String code;

    private String code1;

    private String code2;

    private Double amount;

    private Instant createdat;

    private Instant updatedat;


    private Long productId;

    private Long evenementId;

    private Long paymentmethodId;

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

    public Integer getPaymentmethodid() {
        return paymentmethodid;
    }

    public void setPaymentmethodid(Integer paymentmethodid) {
        this.paymentmethodid = paymentmethodid;
    }

    public Integer getEvenementid() {
        return evenementid;
    }

    public void setEvenementid(Integer evenementid) {
        this.evenementid = evenementid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Instant getExpireat() {
        return expireat;
    }

    public void setExpireat(Instant expireat) {
        this.expireat = expireat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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

        PaymentactionDTO paymentactionDTO = (PaymentactionDTO) o;
        if (paymentactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentactionDTO{" +
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
            ", productId=" + getProductId() +
            ", evenementId=" + getEvenementId() +
            ", paymentmethodId=" + getPaymentmethodId() +
            "}";
    }
}
