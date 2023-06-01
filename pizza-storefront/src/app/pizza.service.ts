import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Order, PizzaDetails } from "./models";

@Injectable()
export class PizzaService {

  http=inject(HttpClient)
  // TODO: Task 3
  // You may add any parameters and return any type from placeOrder() method
  // Do not change the method name
  placeOrder(pizzaDetails: PizzaDetails) {
    
    return this.http.post<any>('/api/order', pizzaDetails)
  }

  // TODO: Task 5
  // You may add any parameters and return any type from getOrders() method
  // Do not change the method name
  getOrders(email:string) {
    const url ='/api/orders/${email}'
    
    return this.http.get<Order[]>(url)
  }

  // TODO: Task 7
  // You may add any parameters and return any type from delivered() method
  // Do not change the method name
  delivered() {
  }

}
