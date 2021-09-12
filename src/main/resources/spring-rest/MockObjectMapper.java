package com.taeza.tools.common.util.srcmaker;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MockObjectMapper {


    MockObjectDTO toDTO(MockObject mockObject);

    MockObject toEntity(MockObjectDTO mockObject);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    void mapUpdate(@MappingTarget MockObject mockObjectFromDB, MockObject mockObject);

}
