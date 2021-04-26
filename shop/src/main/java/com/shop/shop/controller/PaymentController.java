package com.shop.shop.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.shop.shop.common.Utils;
import com.shop.shop.config.PaypalPaymentIntent;
import com.shop.shop.config.PaypalPaymentMethod;
import com.shop.shop.entity.Account;
import com.shop.shop.entity.Cart;
import com.shop.shop.entity.CartItem;
import com.shop.shop.repository.*;
import com.shop.shop.request.OrderRequest;
import com.shop.shop.service.CartItemService;
import com.shop.shop.service.OrderService;
import com.shop.shop.service.PaypalService;
import com.shop.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import response.CartItemDTO;
import response.OrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/")
public class PaymentController {
	
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ProductService productService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartItemRepository cartItemRepository;

	Locale localeEN = new Locale("en", "EN");
	NumberFormat en = NumberFormat.getInstance(localeEN);

	@GetMapping("/payment")
	public String payment(Model model, Pageable pageable){
		OrderRequest orderRequest = new OrderRequest();
		model.addAttribute("orderRequest", orderRequest);
		Page<CartItemDTO> cartItemDTOS = cartItemService.getAllProductByCart(pageable);
		model.addAttribute("cartItemDTOS", cartItemDTOS);
		double total =0.0;
		int soLuong=0;
		for (CartItemDTO item:cartItemDTOS) {
			total+=item.getProduct().getPrice() * (1 - item.getProduct().getDiscount()/ 100);
			soLuong+=item.getQuantity();
		}
		model.addAttribute("quantity", soLuong);
		String tong = en.format(total);
		model.addAttribute("tong", tong);
		return "/payment";
	}
	
	@PostMapping("/payment")
	public String pay(HttpServletRequest request,  Model model,OrderRequest orderRequest){
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		OrderDTO orderDTO = orderService.createOrderByUser(orderRequest);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Account account = accountRepository.findByEmail(authentication.getName());
		Cart cart = cartRepository.findByAccountId(account.getId());
		List<CartItem> cartItemList = cartItemRepository.findAllByCartId(cart.getId());
		double subTotal=0;
		for (CartItem item : cartItemList) {
			subTotal+=item.getProduct().getPrice()*item.getQuantity()*(1-item.getProduct().getDiscount())/100;
		}
		double sum=subTotal+orderRequest.getShipping();
		try {
			Payment payment = paypalService.createPayment(
					sum,
					"USD", 
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description", 
					cancelUrl, 
					successUrl);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/payment";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "cancel";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/payment";
	}
	
}