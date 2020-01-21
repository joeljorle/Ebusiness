package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FollowsMapperTest {

    private FollowsMapper followsMapper;

    @BeforeEach
    public void setUp() {
        followsMapper = new FollowsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(followsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(followsMapper.fromId(null)).isNull();
    }
}
