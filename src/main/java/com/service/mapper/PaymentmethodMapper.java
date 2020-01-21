package com.service.mapper;

import com.domain.*;
import com.service.dto.PaymentmethodDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paymentmethod} and its DTO {@link PaymentmethodDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentmethodMapper extends EntityMapper<PaymentmethodDTO, Paymentmethod> {


    @Mapping(target = "paymentactions", ignore = true)
    @Mapping(target = "removePaymentaction", ignore = true)
    @Mapping(target = "paymentcategories", ignore = true)
    @Mapping(target = "removePaymentcategory", ignore = true)
    Paymentmethod toEntity(PaymentmethodDTO paymentmethodDTO);

    default Paymentmethod fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paymentmethod paymentmethod = new Paymentmethod();
        paymentmethod.setId(id);
        return paymentmethod;
    }
}
