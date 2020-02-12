package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ActusMapperTest {

    private ActusMapper actusMapper;

    @BeforeEach
    public void setUp() {
        actusMapper = new ActusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(actusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(actusMapper.fromId(null)).isNull();
    }
}
