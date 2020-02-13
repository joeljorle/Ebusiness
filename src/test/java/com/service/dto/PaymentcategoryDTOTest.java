package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentcategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentcategoryDTO.class);
        PaymentcategoryDTO paymentcategoryDTO1 = new PaymentcategoryDTO();
        paymentcategoryDTO1.setId(1L);
        PaymentcategoryDTO paymentcategoryDTO2 = new PaymentcategoryDTO();
        assertThat(paymentcategoryDTO1).isNotEqualTo(paymentcategoryDTO2);
        paymentcategoryDTO2.setId(paymentcategoryDTO1.getId());
        assertThat(paymentcategoryDTO1).isEqualTo(paymentcategoryDTO2);
        paymentcategoryDTO2.setId(2L);
        assertThat(paymentcategoryDTO1).isNotEqualTo(paymentcategoryDTO2);
        paymentcategoryDTO1.setId(null);
        assertThat(paymentcategoryDTO1).isNotEqualTo(paymentcategoryDTO2);
    }
}
