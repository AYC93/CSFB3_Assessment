package ibf2022.batch3.assessment.csf.orderbackend.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.OrdersRepository;
import ibf2022.batch3.assessment.csf.orderbackend.respositories.PendingOrdersRepository;
import jakarta.json.Json;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrdersRepository ordRepo;

	@Autowired
	PendingOrdersRepository pendingOrdRepo;

	// TODO: Task 3 - POST /api/order
	@PostMapping("/order")
	public void createOrder(@RequestBody PizzaOrder pizzaOrder, HttpServletRequest req){
		String [] toppingsArr = req.getParameterValues("toppings");
		List<String> toppings = Arrays.asList(toppingsArr);

		PizzaOrder order = new PizzaOrder();
		order.setOrderId("null");// from pricing service, string
		order.setDate(Date.valueOf(LocalDate.now())); // from pricing service, date
		order.setTotal(9f); // from pricing service, float
		order.setName("name");
		order.setSauce("sauce");
		order.setSize(Integer.parseInt("size"));
		order.setComments("comments");
		order.setTopplings(toppings);

		ordRepo.add(order);
		pendingOrdRepo.add(order);
	}

	//Task 4
	@ResponseBody
	@PostMapping(path="/order")
	public ResponseEntity<String> clientPlaceOrder(@RequestBody PizzaOrder pizzaOrder){
		try {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
							.body(Json.createObjectBuilder()
							.add("orderId", pizzaOrder.getOrderId())
							.add("date", pizzaOrder.getDate().toString())
							.add("name", pizzaOrder.getName())
							.add("email", pizzaOrder.getEmail())
							.add("total", pizzaOrder.getTotal())
							.build()
							.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body(Json.createObjectBuilder()
								.add("error", e.getMessage())
								.build().toString());
		}
	}

	@PostMapping(path="/order")
	public ResponseEntity<String> orderProcess(@RequestBody PizzaOrder pizzaOrder){
		
	}


	// TODO: Task 6 - GET /api/orders/<email>


	// TODO: Task 7 - DELETE /api/order/<orderId>

}
