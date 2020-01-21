package com.service.mapper;

import com.domain.*;
import com.service.dto.CategorydetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Categorydetails} and its DTO {@link CategorydetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorydetailsMapper extends EntityMapper<CategorydetailsDTO, Categorydetails> {



    default Categorydetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorydetails categorydetails = new Categorydetails();
        categorydetails.setId(id);
        return categorydetails;
    }
}
