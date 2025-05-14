package com.chainplus.logistics.mapper;

import com.chainplus.logistics.dto.RoutePlanDTO;
import com.chainplus.logistics.entity.RoutePlan;


@Mapper(componentModel = "spring")
public interface RoutePlanMapper {

    RoutePlanDTO toDTO(RoutePlan routePlan);
    RoutePlan toEntity(RoutePlanDTO routePlanDTO);
}
