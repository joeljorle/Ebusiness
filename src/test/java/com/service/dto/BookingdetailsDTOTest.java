package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingdetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingdetailsDTO.class);
        BookingdetailsDTO bookingdetailsDTO1 = new BookingdetailsDTO();
        bookingdetailsDTO1.setId(1L);
        BookingdetailsDTO bookingdetailsDTO2 = new BookingdetailsDTO();
        assertThat(bookingdetailsDTO1).isNotEqualTo(bookingdetailsDTO2);
        bookingdetailsDTO2.setId(bookingdetailsDTO1.getId());
        assertThat(bookingdetailsDTO1).isEqualTo(bookingdetailsDTO2);
        bookingdetailsDTO2.setId(2L);
        assertThat(bookingdetailsDTO1).isNotEqualTo(bookingdetailsDTO2);
        bookingdetailsDTO1.setId(null);
        assertThat(bookingdetailsDTO1).isNotEqualTo(bookingdetailsDTO2);
    }
}
