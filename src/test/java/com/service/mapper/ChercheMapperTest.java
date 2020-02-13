package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ChercheMapperTest {

    private ChercheMapper chercheMapper;

    @BeforeEach
    public void setUp() {
        chercheMapper = new ChercheMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(chercheMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(chercheMapper.fromId(null)).isNull();
    }
}
