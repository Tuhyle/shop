package com.shop.shop.service;

import com.shop.shop.common.MyItem;

import java.util.Date;
import java.util.List;

public interface OrderItemService {
    List<MyItem> reportReceipt(Date date, int limit);
}
