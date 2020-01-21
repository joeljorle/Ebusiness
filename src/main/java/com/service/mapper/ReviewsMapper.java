package com.service.mapper;

import com.domain.*;
import com.service.dto.ReviewsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reviews} and its DTO {@link ReviewsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface ReviewsMapper extends EntityMapper<ReviewsDTO, Reviews> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    ReviewsDTO toDto(Reviews reviews);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Reviews toEntity(ReviewsDTO reviewsDTO);

    default Reviews fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reviews reviews = new Reviews();
        reviews.setId(id);
        return reviews;
    }
}
