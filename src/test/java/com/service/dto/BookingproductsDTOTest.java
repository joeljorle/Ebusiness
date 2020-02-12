package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingproductsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingproductsDTO.class);
        BookingproductsDTO bookingproductsDTO1 = new BookingproductsDTO();
        bookingproductsDTO1.setId(1L);
        BookingproductsDTO bookingproductsDTO2 = new BookingproductsDTO();
        assertThat(bookingproductsDTO1).isNotEqualTo(bookingproductsDTO2);
        bookingproductsDTO2.setId(bookingproductsDTO1.getId());
        assertThat(bookingproductsDTO1).isEqualTo(bookingproductsDTO2);
        bookingproductsDTO2.setId(2L);
        assertThat(bookingproductsDTO1).isNotEqualTo(bookingproductsDTO2);
        bookingproductsDTO1.setId(null);
        assertThat(bookingproductsDTO1).isNotEqualTo(bookingproductsDTO2);
    }
}
