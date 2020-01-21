package com.service.mapper;

import com.domain.*;
import com.service.dto.FilesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Files} and its DTO {@link FilesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FilesMapper extends EntityMapper<FilesDTO, Files> {



    default Files fromId(Long id) {
        if (id == null) {
            return null;
        }
        Files files = new Files();
        files.setId(id);
        return files;
    }
}
