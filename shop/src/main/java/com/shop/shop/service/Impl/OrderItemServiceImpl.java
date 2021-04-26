package com.shop.shop.service.Impl;

import com.shop.shop.common.MyItem;
import com.shop.shop.entity.OrderItem;
import com.shop.shop.repository.OrderItemRepository;
import com.shop.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public List<MyItem> reportReceipt(Date date, int limit) {
        List<MyItem> list = new ArrayList<>();
        for (int i = limit - 1; i >= 0; i--) {
            Date d = subDays(date, i);
            MyItem myItem = new MyItem();
            myItem.setTime(covertD2S(d));
            myItem.setValue(countItemByDate(covertD2S(d)));
            list.add(myItem);
        }
        return list;
    }

    private int countItemByDate(String date) {
        List<OrderItem> orderItems=orderItemRepository.findAllByCreateAt_Date(date);
        return orderItems.size();
    }
    public static Date addDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    public static Date subDays(Date date, int days) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    private String covertD2S(Date date) {
        DateFormat df = new SimpleDateFormat("%y-%M-%d");
        return df.format(date);
    }
}
