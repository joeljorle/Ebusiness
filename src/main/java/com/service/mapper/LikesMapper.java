package com.service.mapper;

import com.domain.*;
import com.service.dto.LikesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Likes} and its DTO {@link LikesDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface LikesMapper extends EntityMapper<LikesDTO, Likes> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    LikesDTO toDto(Likes likes);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Likes toEntity(LikesDTO likesDTO);

    default Likes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Likes likes = new Likes();
        likes.setId(id);
        return likes;
    }
}
