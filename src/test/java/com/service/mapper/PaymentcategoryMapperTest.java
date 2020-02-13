package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentcategoryMapperTest {

    private PaymentcategoryMapper paymentcategoryMapper;

    @BeforeEach
    public void setUp() {
        paymentcategoryMapper = new PaymentcategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(paymentcategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(paymentcategoryMapper.fromId(null)).isNull();
    }
}
