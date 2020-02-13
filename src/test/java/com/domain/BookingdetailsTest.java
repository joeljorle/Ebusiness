package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingdetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bookingdetails.class);
        Bookingdetails bookingdetails1 = new Bookingdetails();
        bookingdetails1.setId(1L);
        Bookingdetails bookingdetails2 = new Bookingdetails();
        bookingdetails2.setId(bookingdetails1.getId());
        assertThat(bookingdetails1).isEqualTo(bookingdetails2);
        bookingdetails2.setId(2L);
        assertThat(bookingdetails1).isNotEqualTo(bookingdetails2);
        bookingdetails1.setId(null);
        assertThat(bookingdetails1).isNotEqualTo(bookingdetails2);
    }
}
