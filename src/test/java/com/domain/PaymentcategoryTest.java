package com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentcategoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paymentcategory.class);
        Paymentcategory paymentcategory1 = new Paymentcategory();
        paymentcategory1.setId(1L);
        Paymentcategory paymentcategory2 = new Paymentcategory();
        paymentcategory2.setId(paymentcategory1.getId());
        assertThat(paymentcategory1).isEqualTo(paymentcategory2);
        paymentcategory2.setId(2L);
        assertThat(paymentcategory1).isNotEqualTo(paymentcategory2);
        paymentcategory1.setId(null);
        assertThat(paymentcategory1).isNotEqualTo(paymentcategory2);
    }
}
