package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingviewTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bookingview.class);
        Bookingview bookingview1 = new Bookingview();
        bookingview1.setId(1L);
        Bookingview bookingview2 = new Bookingview();
        bookingview2.setId(bookingview1.getId());
        assertThat(bookingview1).isEqualTo(bookingview2);
        bookingview2.setId(2L);
        assertThat(bookingview1).isNotEqualTo(bookingview2);
        bookingview1.setId(null);
        assertThat(bookingview1).isNotEqualTo(bookingview2);
    }
}
