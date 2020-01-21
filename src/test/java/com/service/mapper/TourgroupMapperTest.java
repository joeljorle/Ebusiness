package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TourgroupMapperTest {

    private TourgroupMapper tourgroupMapper;

    @BeforeEach
    public void setUp() {
        tourgroupMapper = new TourgroupMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(tourgroupMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tourgroupMapper.fromId(null)).isNull();
    }
}
