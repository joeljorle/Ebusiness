package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CategorydetailsMapperTest {

    private CategorydetailsMapper categorydetailsMapper;

    @BeforeEach
    public void setUp() {
        categorydetailsMapper = new CategorydetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(categorydetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(categorydetailsMapper.fromId(null)).isNull();
    }
}
