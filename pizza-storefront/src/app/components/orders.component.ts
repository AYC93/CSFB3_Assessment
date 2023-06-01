import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PizzaService } from '../pizza.service';
import { Order } from '../models';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  activatedRoute = inject(ActivatedRoute)

  pizzaSvc = inject(PizzaService)

  email=''

  number= 1.00

  order$!: Observable<Order[]>

  ngOnInit(): void {
      this.email = this.activatedRoute.snapshot.params['email']
      this.order$ = this.pizzaSvc.getOrders(this.email)
  }
}
