package com.service.mapper;

import com.domain.*;
import com.service.dto.BookingviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bookingview} and its DTO {@link BookingviewDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookingviewMapper extends EntityMapper<BookingviewDTO, Bookingview> {



    default Bookingview fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bookingview bookingview = new Bookingview();
        bookingview.setId(id);
        return bookingview;
    }
}
