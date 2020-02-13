package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingdetailsMapperTest {

    private BookingdetailsMapper bookingdetailsMapper;

    @BeforeEach
    public void setUp() {
        bookingdetailsMapper = new BookingdetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(bookingdetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookingdetailsMapper.fromId(null)).isNull();
    }
}
