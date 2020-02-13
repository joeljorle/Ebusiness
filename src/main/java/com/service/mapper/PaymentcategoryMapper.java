package com.service.mapper;


import com.domain.*;
import com.service.dto.PaymentcategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paymentcategory} and its DTO {@link PaymentcategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, PaymentmethodMapper.class})
public interface PaymentcategoryMapper extends EntityMapper<PaymentcategoryDTO, Paymentcategory> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "paymentmethod.id", target = "paymentmethodId")
    PaymentcategoryDTO toDto(Paymentcategory paymentcategory);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "paymentmethodId", target = "paymentmethod")
    Paymentcategory toEntity(PaymentcategoryDTO paymentcategoryDTO);

    default Paymentcategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paymentcategory paymentcategory = new Paymentcategory();
        paymentcategory.setId(id);
        return paymentcategory;
    }
}
