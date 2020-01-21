package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PaymentactionMapperTest {

    private PaymentactionMapper paymentactionMapper;

    @BeforeEach
    public void setUp() {
        paymentactionMapper = new PaymentactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(paymentactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentactionMapper.fromId(null)).isNull();
    }
}
