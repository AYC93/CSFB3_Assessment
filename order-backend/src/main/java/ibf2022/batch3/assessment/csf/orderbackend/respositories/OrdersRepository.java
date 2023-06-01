package ibf2022.batch3.assessment.csf.orderbackend.respositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
	/*
	 * db.orders.find(
	 * { delivered: { $ne: true } },
	 * { orderId: 1, date: 1, email: 1 , total: 1 } 
	 * ).sort({ date: -1 });
	 */
	public List<PizzaOrder> getPendingOrdersByEmail(String email) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("delivered").ne(true));
		query.with(Sort.by(Sort.Direction.DESC, "date"));
	
		query.fields().include("orderId", "date", "email","total");
	
		List<PizzaOrder> orders = mongoTemplate.find(query, PizzaOrder.class);
		return orders;
	}

	// TODO: Task 7
	// WARNING: Do not change the method's signature.
	// Write the native MongoDB query in the comment below
	// Native MongoDB query here for markOrderDelivered()
	public boolean markOrderDelivered(String orderId) {

		return false;
	}

}
