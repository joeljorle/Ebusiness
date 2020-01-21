package com.service.dto;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.domain.Currency} entity.
 */
public class CurrencyDTO implements Serializable {

    private Long id;

    @NotNull
    private String slug;

    private String name;

    private String abrev;

    @Lob
    private String logo;

    @Lob
    private byte[] logodata;

    private String logodataContentType;
    private Instant createdat;

    private Instant updatedat;


    private Long categoryId;

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

    public String getAbrev() {
        return abrev;
    }

    public void setAbrev(String abrev) {
        this.abrev = abrev;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (currencyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", slug='" + getSlug() + "'" +
            ", name='" + getName() + "'" +
            ", abrev='" + getAbrev() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", createdat='" + getCreatedat() + "'" +
            ", updatedat='" + getUpdatedat() + "'" +
            ", categoryId=" + getCategoryId() +
            "}";
    }
}
