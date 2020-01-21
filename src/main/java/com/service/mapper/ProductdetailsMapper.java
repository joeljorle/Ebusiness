package com.service.mapper;

import com.domain.*;
import com.service.dto.ProductdetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Productdetails} and its DTO {@link ProductdetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProductdetailsMapper extends EntityMapper<ProductdetailsDTO, Productdetails> {



    default Productdetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        Productdetails productdetails = new Productdetails();
        productdetails.setId(id);
        return productdetails;
    }
}
