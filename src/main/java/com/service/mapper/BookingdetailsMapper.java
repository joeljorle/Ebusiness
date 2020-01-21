package com.service.mapper;

import com.domain.*;
import com.service.dto.BookingdetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bookingdetails} and its DTO {@link BookingdetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookingdetailsMapper extends EntityMapper<BookingdetailsDTO, Bookingdetails> {



    default Bookingdetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bookingdetails bookingdetails = new Bookingdetails();
        bookingdetails.setId(id);
        return bookingdetails;
    }
}
