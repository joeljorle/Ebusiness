package com.service.mapper;

import com.domain.*;
import com.service.dto.ActusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Actus} and its DTO {@link ActusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActusMapper extends EntityMapper<ActusDTO, Actus> {



    default Actus fromId(Long id) {
        if (id == null) {
            return null;
        }
        Actus actus = new Actus();
        actus.setId(id);
        return actus;
    }
}
