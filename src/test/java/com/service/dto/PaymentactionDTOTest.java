package com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.web.rest.TestUtil;

public class PaymentactionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentactionDTO.class);
        PaymentactionDTO paymentactionDTO1 = new PaymentactionDTO();
        paymentactionDTO1.setId(1L);
        PaymentactionDTO paymentactionDTO2 = new PaymentactionDTO();
        assertThat(paymentactionDTO1).isNotEqualTo(paymentactionDTO2);
        paymentactionDTO2.setId(paymentactionDTO1.getId());
        assertThat(paymentactionDTO1).isEqualTo(paymentactionDTO2);
        paymentactionDTO2.setId(2L);
        assertThat(paymentactionDTO1).isNotEqualTo(paymentactionDTO2);
        paymentactionDTO1.setId(null);
        assertThat(paymentactionDTO1).isNotEqualTo(paymentactionDTO2);
    }
}
