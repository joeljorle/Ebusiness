package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TourMapperTest {

    private TourMapper tourMapper;

    @BeforeEach
    public void setUp() {
        tourMapper = new TourMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tourMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tourMapper.fromId(null)).isNull();
    }
}
