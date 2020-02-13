package com.service.mapper;


import com.domain.*;
import com.service.dto.PaymentactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Paymentaction} and its DTO {@link PaymentactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, EvenementMapper.class, PaymentmethodMapper.class})
public interface PaymentactionMapper extends EntityMapper<PaymentactionDTO, Paymentaction> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "evenement.id", target = "evenementId")
    @Mapping(source = "paymentmethod.id", target = "paymentmethodId")
    PaymentactionDTO toDto(Paymentaction paymentaction);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "evenementId", target = "evenement")
    @Mapping(source = "paymentmethodId", target = "paymentmethod")
    Paymentaction toEntity(PaymentactionDTO paymentactionDTO);

    default Paymentaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Paymentaction paymentaction = new Paymentaction();
        paymentaction.setId(id);
        return paymentaction;
    }
}
