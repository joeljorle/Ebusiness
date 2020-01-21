package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ShippingMapperTest {

    private ShippingMapper shippingMapper;

    @BeforeEach
    public void setUp() {
        shippingMapper = new ShippingMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(shippingMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(shippingMapper.fromId(null)).isNull();
    }
}
