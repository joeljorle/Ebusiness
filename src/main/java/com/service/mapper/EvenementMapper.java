package com.service.mapper;

import com.domain.*;
import com.service.dto.EvenementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Evenement} and its DTO {@link EvenementDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, CurrencyMapper.class, LocationMapper.class, CategoryMapper.class, ProductMapper.class})
public interface EvenementMapper extends EntityMapper<EvenementDTO, Evenement> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    EvenementDTO toDto(Evenement evenement);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "tours", ignore = true)
    @Mapping(target = "removeTour", ignore = true)
    @Mapping(target = "tourgroups", ignore = true)
    @Mapping(target = "removeTourgroup", ignore = true)
    @Mapping(target = "tourcategories", ignore = true)
    @Mapping(target = "removeTourcategory", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "removeLikes", ignore = true)
    @Mapping(target = "ratings", ignore = true)
    @Mapping(target = "removeRatings", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "follows", ignore = true)
    @Mapping(target = "removeFollows", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "removeBooking", ignore = true)
    @Mapping(target = "paymentactions", ignore = true)
    @Mapping(target = "removePaymentaction", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    Evenement toEntity(EvenementDTO evenementDTO);

    default Evenement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Evenement evenement = new Evenement();
        evenement.setId(id);
        return evenement;
    }
}
