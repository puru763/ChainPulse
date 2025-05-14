package com.chainplus.logistics.mapper;

import com.chainplus.logistics.dto.DeliveryDTO;
import com.chainplus.logistics.entity.Delivery;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryDTO toDTO(Delivery delivery);
    Delivery toEntity(DeliveryDTO deliveryDTO);
}


