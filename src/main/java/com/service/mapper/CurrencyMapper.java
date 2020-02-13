package com.service.mapper;


import com.domain.*;
import com.service.dto.CurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {

    @Mapping(source = "category.id", target = "categoryId")
    CurrencyDTO toDto(Currency currency);

    @Mapping(source = "categoryId", target = "category")
    Currency toEntity(CurrencyDTO currencyDTO);

    default Currency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(id);
        return currency;
    }
}
