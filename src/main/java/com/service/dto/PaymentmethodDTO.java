package com.service.dto;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.domain.Paymentmethod} entity.
 */
public class PaymentmethodDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    private String name;

    private String logo;

    @Lob
    private byte[] logodata;

    private String logodataContentType;
    private Instant createdat;

    private Instant updatedat;

    /**
     * Payments to many relationship.
     */
    @ApiModelProperty(value = "Payments to many relationship.")

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public byte[] getLogodata() {
        return logodata;
    }

    public void setLogodata(byte[] logodata) {
        this.logodata = logodata;
    }

    public String getLogodataContentType() {
        return logodataContentType;
    }

    public void setLogodataContentType(String logodataContentType) {
        this.logodataContentType = logodataContentType;
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

        PaymentmethodDTO paymentmethodDTO = (PaymentmethodDTO) o;
        if (paymentmethodDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentmethodDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentmethodDTO{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            "}";
    }
}
