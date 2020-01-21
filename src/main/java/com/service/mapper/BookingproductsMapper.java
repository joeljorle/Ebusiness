package com.service.mapper;

import com.domain.*;
import com.service.dto.BookingproductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bookingproducts} and its DTO {@link BookingproductsDTO}.
 */
@Mapper(componentModel = "spring", uses = {BookingMapper.class})
public interface BookingproductsMapper extends EntityMapper<BookingproductsDTO, Bookingproducts> {

    @Mapping(source = "booking.id", target = "bookingId")
    BookingproductsDTO toDto(Bookingproducts bookingproducts);

    @Mapping(source = "bookingId", target = "booking")
    Bookingproducts toEntity(BookingproductsDTO bookingproductsDTO);

    default Bookingproducts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bookingproducts bookingproducts = new Bookingproducts();
        bookingproducts.setId(id);
        return bookingproducts;
    }
}
