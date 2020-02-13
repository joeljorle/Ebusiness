package com.service.mapper;


import com.domain.*;
import com.service.dto.TourgroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tourgroup} and its DTO {@link TourgroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, LocationMapper.class, CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface TourgroupMapper extends EntityMapper<TourgroupDTO, Tourgroup> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "files.tourgroupid", target = "filesTourgroupid")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    TourgroupDTO toDto(Tourgroup tourgroup);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "locationId", target = "location")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Tourgroup toEntity(TourgroupDTO tourgroupDTO);

    default Tourgroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tourgroup tourgroup = new Tourgroup();
        tourgroup.setId(id);
        return tourgroup;
    }
}
