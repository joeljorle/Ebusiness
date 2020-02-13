package com.service.mapper;


import com.domain.*;
import com.service.dto.TourDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tour} and its DTO {@link TourDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, CurrencyMapper.class, LocationMapper.class, CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface TourMapper extends EntityMapper<TourDTO, Tour> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "files.tourid", target = "filesTourid")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    TourDTO toDto(Tour tour);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Tour toEntity(TourDTO tourDTO);

    default Tour fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tour tour = new Tour();
        tour.setId(id);
        return tour;
    }
}
