package com.service.mapper;


import com.domain.*;
import com.service.dto.CartproductsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cartproducts} and its DTO {@link CartproductsDTO}.
 */
@Mapper(componentModel = "spring", uses = {CartMapper.class})
public interface CartproductsMapper extends EntityMapper<CartproductsDTO, Cartproducts> {

    @Mapping(source = "cart.id", target = "cartId")
    CartproductsDTO toDto(Cartproducts cartproducts);

    @Mapping(source = "cartId", target = "cart")
    Cartproducts toEntity(CartproductsDTO cartproductsDTO);

    default Cartproducts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cartproducts cartproducts = new Cartproducts();
        cartproducts.setId(id);
        return cartproducts;
    }
}
