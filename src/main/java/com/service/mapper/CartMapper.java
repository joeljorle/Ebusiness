package com.service.mapper;


import com.domain.*;
import com.service.dto.CartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class, CategoryMapper.class})
public interface CartMapper extends EntityMapper<CartDTO, Cart> {

    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "category.id", target = "categoryId")
    CartDTO toDto(Cart cart);

    @Mapping(source = "currencyId", target = "currency")
    @Mapping(target = "cartproducts", ignore = true)
    @Mapping(target = "removeCartproducts", ignore = true)
    @Mapping(source = "categoryId", target = "category")
    Cart toEntity(CartDTO cartDTO);

    default Cart fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setId(id);
        return cart;
    }
}
