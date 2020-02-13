package com.service.mapper;


import com.domain.*;
import com.service.dto.LocationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Location} and its DTO {@link LocationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocationMapper extends EntityMapper<LocationDTO, Location> {


    @Mapping(target = "category", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "evenement", ignore = true)
    @Mapping(target = "profile", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "tourgroup", ignore = true)
    Location toEntity(LocationDTO locationDTO);

    default Location fromId(Long id) {
        if (id == null) {
            return null;
        }
        Location location = new Location();
        location.setId(id);
        return location;
    }
}
