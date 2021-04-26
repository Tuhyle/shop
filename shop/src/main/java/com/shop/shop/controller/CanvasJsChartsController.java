// CanvasJsChartsController.java
package com.shop.shop.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.shop.shop.common.Ponto;
import com.shop.shop.entity.CartItem;
import com.shop.shop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class CanvasJsChartsController {
	@Autowired
	CartItemRepository cartItemRepository;
    @RequestMapping(value = "/plot", method = RequestMethod.GET)
    public String getDataPlot(ModelMap model) throws IOException {

        List<Ponto> pontos = new ArrayList<>();

		for(int i=2015 ; i< LocalDate.now().getYear();i++){
//			List<CartItem> cartItems=cartItemRepository.findAllByCreateAtYear(i);
//			Ponto ponto = new Ponto();
//			ponto.setX(i);
//			ponto.setY(cartItems.size());
		}
        model.addAttribute("pontos", pontos);

        return "/admin/chartName";
    }
}