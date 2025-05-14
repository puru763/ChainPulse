package com.chainplus.logistics.service.Impl;

import com.chainplus.logistics.dto.DeliveryDTO;
import com.chainplus.logistics.entity.Delivery;
import com.chainplus.logistics.entity.DeliveryStatus;
import com.chainplus.logistics.exception.ResourceNotFoundException;
import com.chainplus.logistics.mapper.DeliveryMapper;
import com.chainplus.logistics.repository.DeliveryRepository;
import com.chainplus.logistics.service.DeliveryService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public DeliveryDTO getDeliveryByOrderId(Long orderId) {
        try {
            Delivery delivery = deliveryRepository.findByOrderId(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Delivery not found for Order ID: " + orderId));
            return deliveryMapper.toDTO(delivery);
        } catch (Exception e) {
            throw new ServiceException("Error fetching delivery details", e);
        }
    }

//    @Override
//    public void updateDeliveryStatus(Long deliveryId, DeliveryStatus status) {
//        try {
//            Delivery delivery = deliveryRepository.findById(deliveryId)
//                    .orElseThrow(() -> new ResourceNotFoundException("Delivery not found for ID: " + deliveryId));
//
//            // Using Mapper for clean conversion without manual setting
//            DeliveryDTO deliveryDTO = deliveryMapper.toDTO(delivery);
//            deliveryDTO.setStatus(status);
//            deliveryDTO.setLastUpdated(new Timestamp(System.currentTimeMillis()));
//
//            Delivery updatedDelivery = deliveryMapper.toEntity(deliveryDTO);
//            deliveryRepository.save(updatedDelivery);
//        } catch (Exception e) {
//            throw new ServiceException("Error updating delivery status", e);
//        }
//    }
}
