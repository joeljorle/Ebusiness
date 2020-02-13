package com.service.mapper;


import com.domain.*;
import com.service.dto.FollowsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Follows} and its DTO {@link FollowsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface FollowsMapper extends EntityMapper<FollowsDTO, Follows> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    FollowsDTO toDto(Follows follows);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Follows toEntity(FollowsDTO followsDTO);

    default Follows fromId(Long id) {
        if (id == null) {
            return null;
        }
        Follows follows = new Follows();
        follows.setId(id);
        return follows;
    }
}
