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
import ibf2022.batch3.assessment.csf.orderbackend.services.OrderingService;
import jakarta.json.Json;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class OrderController {

	@Autowired
	OrdersRepository ordRepo;

	@Autowired
	OrderingService ordSvc;

	@Autowired
	PendingOrdersRepository pendingOrdRepo;

	// TODO: Task 3 - POST /api/order
	@PostMapping("/order")
	public ResponseEntity<String> placeOrder(@RequestBody PizzaOrder pizzaOrder, HttpServletRequest req) {
		try {
			ordSvc.placeOrder(pizzaOrder);
			String[] toppingsArr = req.getParameterValues("toppings");
			List<String> toppings = Arrays.asList(toppingsArr);

			PizzaOrder order = new PizzaOrder();
			order.setOrderId(order.getOrderId()); // from pricing service, string
			order.setDate(order.getDate()); // from pricing service, date
			order.setTotal(order.getTotal()); // from pricing service, float
			order.setName(pizzaOrder.getName());
			order.setSauce(pizzaOrder.getSauce());
			order.setSize(pizzaOrder.getSize());
			order.setComments(pizzaOrder.getComments());
			order.setTopplings(toppings);

			ordRepo.add(order);
			pendingOrdRepo.add(order);

			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(Json.createObjectBuilder()
							.add("orderId", order.getOrderId())
							.add("date", order.getDate().toString())
							.add("name", order.getName())
							.add("email", order.getEmail())
							.add("total", order.getTotal())
							.build()
							.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Json.createObjectBuilder()
							.add("error", e.getMessage())
							.build()
							.toString());
		}
	}

	// TODO: Task 6 - GET /api/orders/<email>
	
	// TODO: Task 7 - DELETE /api/order/<orderId>

}
