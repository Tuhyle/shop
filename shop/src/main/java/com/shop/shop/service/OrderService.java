package com.shop.shop.service;

import com.shop.shop.request.OrderRequest;
import response.OrderDTO;

public interface OrderService {
    OrderDTO createOrderByUser(OrderRequest orderRequest);



}
