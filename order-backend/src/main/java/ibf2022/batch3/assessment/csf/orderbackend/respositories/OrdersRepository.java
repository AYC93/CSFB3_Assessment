package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch3.assessment.csf.orderbackend.models.PizzaOrder;

@Repository
public class OrdersRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	

	/*
	 * mongodb query:
	 * db.pizzaOrders.insertOne({
	 * _id: "1",
	 * date: "Tue Jul 12 18:35:37 IST 2016" ,
	 * total: 23.23,
	 * name: "Ang Yi Ci",
	 * email: "ayc.leo@gmail.com" ,
	 * sauce: "classic",
	 * size: 1,
	 * crust: "thick",
	 * comments: "Make it quick",
	 * toppings: "[ chicken ]"
	 * })
	 */
	
	 // orderId to be auto generated in later task

	// TODO: Task 3
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for add()
	// (orderId, date, name, email, sauce, size, thickCrust, toppings, comments,
	// total)
	public void add(PizzaOrder order) {
		mongoTemplate.insert(order, "orders");
	}

	// TODO: Task 6
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for getPendingOrdersByEmail()
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {

		return null;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}

}
