package com.service.mapper;


import com.domain.*;
import com.service.dto.ShippingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Shipping} and its DTO {@link ShippingDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ShippingMapper extends EntityMapper<ShippingDTO, Shipping> {

    @Mapping(source = "category.id", target = "categoryId")
    ShippingDTO toDto(Shipping shipping);

    @Mapping(source = "categoryId", target = "category")
    Shipping toEntity(ShippingDTO shippingDTO);

    default Shipping fromId(Long id) {
        if (id == null) {
            return null;
        }
        Shipping shipping = new Shipping();
        shipping.setId(id);
        return shipping;
    }
}
