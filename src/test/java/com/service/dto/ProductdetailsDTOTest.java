package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class ProductdetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductdetailsDTO.class);
        ProductdetailsDTO productdetailsDTO1 = new ProductdetailsDTO();
        productdetailsDTO1.setId(1L);
        ProductdetailsDTO productdetailsDTO2 = new ProductdetailsDTO();
        assertThat(productdetailsDTO1).isNotEqualTo(productdetailsDTO2);
        productdetailsDTO2.setId(productdetailsDTO1.getId());
        assertThat(productdetailsDTO1).isEqualTo(productdetailsDTO2);
        productdetailsDTO2.setId(2L);
        assertThat(productdetailsDTO1).isNotEqualTo(productdetailsDTO2);
        productdetailsDTO1.setId(null);
        assertThat(productdetailsDTO1).isNotEqualTo(productdetailsDTO2);
    }
}
