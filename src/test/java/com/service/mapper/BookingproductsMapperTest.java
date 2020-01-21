package com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class BookingproductsMapperTest {

    private BookingproductsMapper bookingproductsMapper;

    @BeforeEach
    public void setUp() {
        bookingproductsMapper = new BookingproductsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(bookingproductsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(bookingproductsMapper.fromId(null)).isNull();
    }
}
