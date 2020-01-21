package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BookingviewMapperTest {

    private BookingviewMapper bookingviewMapper;

    @BeforeEach
    public void setUp() {
        bookingviewMapper = new BookingviewMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(bookingviewMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookingviewMapper.fromId(null)).isNull();
    }
}
