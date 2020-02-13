package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentmethodTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paymentmethod.class);
        Paymentmethod paymentmethod1 = new Paymentmethod();
        paymentmethod1.setId(1L);
        Paymentmethod paymentmethod2 = new Paymentmethod();
        paymentmethod2.setId(paymentmethod1.getId());
        assertThat(paymentmethod1).isEqualTo(paymentmethod2);
        paymentmethod2.setId(2L);
        assertThat(paymentmethod1).isNotEqualTo(paymentmethod2);
        paymentmethod1.setId(null);
        assertThat(paymentmethod1).isNotEqualTo(paymentmethod2);
    }
}
