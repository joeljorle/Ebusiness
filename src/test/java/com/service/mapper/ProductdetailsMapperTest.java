package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductdetailsMapperTest {

    private ProductdetailsMapper productdetailsMapper;

    @BeforeEach
    public void setUp() {
        productdetailsMapper = new ProductdetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(productdetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(productdetailsMapper.fromId(null)).isNull();
    }
}
