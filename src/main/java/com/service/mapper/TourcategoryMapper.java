package com.service.mapper;


import com.domain.*;
import com.service.dto.TourcategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tourcategory} and its DTO {@link TourcategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {FilesMapper.class, CategoryMapper.class, ProductMapper.class, EvenementMapper.class})
public interface TourcategoryMapper extends EntityMapper<TourcategoryDTO, Tourcategory> {

    @Mapping(source = "files.id", target = "filesId")
    @Mapping(source = "files.tourcategoryid", target = "filesTourcategoryid")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    TourcategoryDTO toDto(Tourcategory tourcategory);

    @Mapping(source = "filesId", target = "files")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    Tourcategory toEntity(TourcategoryDTO tourcategoryDTO);

    default Tourcategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tourcategory tourcategory = new Tourcategory();
        tourcategory.setId(id);
        return tourcategory;
    }
}
