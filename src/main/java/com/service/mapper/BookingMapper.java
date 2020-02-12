package com.service.mapper;


import com.domain.*;
import com.service.dto.BookingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and its DTO {@link BookingDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class, BookingviewMapper.class, BookingdetailsMapper.class, CategoryMapper.class, EvenementMapper.class})
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {

    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "bookingview.id", target = "bookingviewId")
    @Mapping(source = "bookingdetails.id", target = "bookingdetailsId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "evenement.id", target = "evenementId")
    BookingDTO toDto(Booking booking);

    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "bookingviewId", target = "bookingview")
    @Mapping(source = "bookingdetailsId", target = "bookingdetails")
    @Mapping(target = "bookingproducts", ignore = true)
    @Mapping(target = "removeBookingproducts", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "evenementId", target = "evenement")
    Booking toEntity(BookingDTO bookingDTO);

    default Booking fromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }
}
