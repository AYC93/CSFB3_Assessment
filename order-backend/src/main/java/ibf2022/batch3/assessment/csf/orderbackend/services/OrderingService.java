package ibf2022.batch3.assessment.csf.orderbackend.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;

public class OrderingService {

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private PendingOrdersRepository pendingOrdersRepo;

	public static final String URL = "https://pizza-pricing-production.up.railway.app";

	// TODO: Task 5
	// WARNING: DO NOT CHANGE THE METHOD'S SIGNATURE
	public PizzaOrder placeOrder(PizzaOrder order) throws OrderException {
		String pizzaCrust;
		String commentInput;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		if (order.getThickCrust()){
			pizzaCrust = "true";
		}
		else pizzaCrust = "false";

		if (order.getComments().isBlank()){
			commentInput = "";
		}
		else commentInput = order.getComments();

		MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
		formData.add("name", order.getName());
		formData.add("email", order.getEmail());
		formData.add("sauce", order.getSauce());
		formData.add("size", order.getSize().toString());
		formData.add("thickCrust", pizzaCrust);
		formData.addAll("toppings", order.getTopplings()
										.stream()
										.collect(Collectors.toList()));
		formData.add("comments", commentInput);

		RequestEntity<Void> req = RequestEntity.get(URL)
							.accept(MediaType.TEXT_PLAIN)
							.build();
		

		MultiValueMap<String, String> req = new HttpEnt

		return null;
	}

	public boolean thickCrustChecker(PizzaOrder order){
		return order.getThickCrust();
	}

	// For Task 6
	// WARNING: Do not change the method's signature or its implemenation
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		return ordersRepo.getPendingOrdersByEmail(email);
	}

	// For Task 7
	// WARNING: Do not change the method's signature or its implemenation
	public boolean markOrderDelivered(String orderId) {
		return ordersRepo.markOrderDelivered(orderId) && pendingOrdersRepo.delete(orderId);
	}


}
