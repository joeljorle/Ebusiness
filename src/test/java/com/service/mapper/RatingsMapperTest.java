package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RatingsMapperTest {

    private RatingsMapper ratingsMapper;

    @BeforeEach
    public void setUp() {
        ratingsMapper = new RatingsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ratingsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ratingsMapper.fromId(null)).isNull();
    }
}
