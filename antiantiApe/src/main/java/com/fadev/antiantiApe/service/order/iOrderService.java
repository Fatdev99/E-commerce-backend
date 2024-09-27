package com.fadev.antiantiApe.service.order;

import com.fadev.antiantiApe.dto.OrderDTO;
import com.fadev.antiantiApe.model.Order;

import java.util.List;

public interface iOrderService {
    Order placeOrder(Long userId);
    OrderDTO getOrder(Long orderId);
    List<OrderDTO> getUserOrders(Long userId);

    OrderDTO convertToDTO(Order order);
}
