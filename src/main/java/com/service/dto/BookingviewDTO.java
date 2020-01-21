package com.service.dto;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.domain.Bookingview} entity.
 */
public class BookingviewDTO implements Serializable {

    private Long id;

    private String title;

    private String subtitle;

    private String logo;

    @Lob
    private byte[] logodata;

    private String logodataContentType;
    private String bg;

    @Lob
    private byte[] bgdata;

    private String bgdataContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public byte[] getBgdata() {
        return bgdata;
    }

    public void setBgdata(byte[] bgdata) {
        this.bgdata = bgdata;
    }

    public String getBgdataContentType() {
        return bgdataContentType;
    }

    public void setBgdataContentType(String bgdataContentType) {
        this.bgdataContentType = bgdataContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingviewDTO bookingviewDTO = (BookingviewDTO) o;
        if (bookingviewDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingviewDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingviewDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", subtitle='" + getSubtitle() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logodata='" + getLogodata() + "'" +
            ", bg='" + getBg() + "'" +
            ", bgdata='" + getBgdata() + "'" +
            "}";
    }
}
