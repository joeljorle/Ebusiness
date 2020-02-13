package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class BookingproductsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bookingproducts.class);
        Bookingproducts bookingproducts1 = new Bookingproducts();
        bookingproducts1.setId(1L);
        Bookingproducts bookingproducts2 = new Bookingproducts();
        bookingproducts2.setId(bookingproducts1.getId());
        assertThat(bookingproducts1).isEqualTo(bookingproducts2);
        bookingproducts2.setId(2L);
        assertThat(bookingproducts1).isNotEqualTo(bookingproducts2);
        bookingproducts1.setId(null);
        assertThat(bookingproducts1).isNotEqualTo(bookingproducts2);
    }
}
