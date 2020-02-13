package com.service.mapper;


import com.domain.*;
import com.service.dto.RatingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ratings} and its DTO {@link RatingsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface RatingsMapper extends EntityMapper<RatingsDTO, Ratings> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    RatingsDTO toDto(Ratings ratings);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Ratings toEntity(RatingsDTO ratingsDTO);

    default Ratings fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ratings ratings = new Ratings();
        ratings.setId(id);
        return ratings;
    }
}
