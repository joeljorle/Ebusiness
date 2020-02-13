package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentmethodDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentmethodDTO.class);
        PaymentmethodDTO paymentmethodDTO1 = new PaymentmethodDTO();
        paymentmethodDTO1.setId(1L);
        PaymentmethodDTO paymentmethodDTO2 = new PaymentmethodDTO();
        assertThat(paymentmethodDTO1).isNotEqualTo(paymentmethodDTO2);
        paymentmethodDTO2.setId(paymentmethodDTO1.getId());
        assertThat(paymentmethodDTO1).isEqualTo(paymentmethodDTO2);
        paymentmethodDTO2.setId(2L);
        assertThat(paymentmethodDTO1).isNotEqualTo(paymentmethodDTO2);
        paymentmethodDTO1.setId(null);
        assertThat(paymentmethodDTO1).isNotEqualTo(paymentmethodDTO2);
    }
}
