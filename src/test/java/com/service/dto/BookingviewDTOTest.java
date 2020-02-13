package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingviewDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BookingviewDTO.class);
        BookingviewDTO bookingviewDTO1 = new BookingviewDTO();
        bookingviewDTO1.setId(1L);
        BookingviewDTO bookingviewDTO2 = new BookingviewDTO();
        assertThat(bookingviewDTO1).isNotEqualTo(bookingviewDTO2);
        bookingviewDTO2.setId(bookingviewDTO1.getId());
        assertThat(bookingviewDTO1).isEqualTo(bookingviewDTO2);
        bookingviewDTO2.setId(2L);
        assertThat(bookingviewDTO1).isNotEqualTo(bookingviewDTO2);
        bookingviewDTO1.setId(null);
        assertThat(bookingviewDTO1).isNotEqualTo(bookingviewDTO2);
    }
}
