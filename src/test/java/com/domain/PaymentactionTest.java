package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paymentaction.class);
        Paymentaction paymentaction1 = new Paymentaction();
        paymentaction1.setId(1L);
        Paymentaction paymentaction2 = new Paymentaction();
        paymentaction2.setId(paymentaction1.getId());
        assertThat(paymentaction1).isEqualTo(paymentaction2);
        paymentaction2.setId(2L);
        assertThat(paymentaction1).isNotEqualTo(paymentaction2);
        paymentaction1.setId(null);
        assertThat(paymentaction1).isNotEqualTo(paymentaction2);
    }
}
