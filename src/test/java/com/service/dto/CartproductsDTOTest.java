package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class CartproductsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CartproductsDTO.class);
        CartproductsDTO cartproductsDTO1 = new CartproductsDTO();
        cartproductsDTO1.setId(1L);
        CartproductsDTO cartproductsDTO2 = new CartproductsDTO();
        assertThat(cartproductsDTO1).isNotEqualTo(cartproductsDTO2);
        cartproductsDTO2.setId(cartproductsDTO1.getId());
        assertThat(cartproductsDTO1).isEqualTo(cartproductsDTO2);
        cartproductsDTO2.setId(2L);
        assertThat(cartproductsDTO1).isNotEqualTo(cartproductsDTO2);
        cartproductsDTO1.setId(null);
        assertThat(cartproductsDTO1).isNotEqualTo(cartproductsDTO2);
    }
}
