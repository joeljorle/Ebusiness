package com.service.mapper;

import com.domain.*;
import com.service.dto.ChercheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cherche} and its DTO {@link ChercheDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChercheMapper extends EntityMapper<ChercheDTO, Cherche> {



    default Cherche fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cherche cherche = new Cherche();
        cherche.setId(id);
        return cherche;
    }
}
