package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TourcategoryMapperTest {

    private TourcategoryMapper tourcategoryMapper;

    @BeforeEach
    public void setUp() {
        tourcategoryMapper = new TourcategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tourcategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tourcategoryMapper.fromId(null)).isNull();
    }
}
