package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class CartproductsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cartproducts.class);
        Cartproducts cartproducts1 = new Cartproducts();
        cartproducts1.setId(1L);
        Cartproducts cartproducts2 = new Cartproducts();
        cartproducts2.setId(cartproducts1.getId());
        assertThat(cartproducts1).isEqualTo(cartproducts2);
        cartproducts2.setId(2L);
        assertThat(cartproducts1).isNotEqualTo(cartproducts2);
        cartproducts1.setId(null);
        assertThat(cartproducts1).isNotEqualTo(cartproducts2);
    }
}
