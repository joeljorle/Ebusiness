package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PaymentmethodMapperTest {

    private PaymentmethodMapper paymentmethodMapper;

    @BeforeEach
    public void setUp() {
        paymentmethodMapper = new PaymentmethodMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(paymentmethodMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentmethodMapper.fromId(null)).isNull();
    }
}
