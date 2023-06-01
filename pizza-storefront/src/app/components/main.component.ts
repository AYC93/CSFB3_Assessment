import { Component, OnInit, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { PizzaDetails } from '../models';
import { Router } from '@angular/router';
import { PizzaService } from '../pizza.service';
import { firstValueFrom } from 'rxjs';

const SIZES: string[] = [
  "Personal - 6 inches",
  "Regular - 9 inches",
  "Large - 12 inches",
  "Extra Large - 15 inches"
]

const PIZZA_TOPPINGS: string[] = [
  'chicken', 'seafood', 'beef', 'vegetables',
  'cheese', 'arugula', 'pineapple'
]

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  pizzaSize = SIZES[0]
  form!: FormGroup
  toppingsArr!: FormArray

  router = inject(Router)
  fb = inject(FormBuilder)
  pizzaService = inject(PizzaService)

  constructor() { }

  updateSize(size: string) {
    this.pizzaSize = SIZES[parseInt(size)]
  }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  createForm() {
    return this.fb.group({
      name: ['Ang Yi Ci', Validators.required],
      email: ['ayc.leo@gmail.com', [Validators.required, Validators.email]],
      size: [1, Validators.required],
      base: ['', Validators.required],
      sauce: ['', Validators.required],
      toppings: [this.toppingsArr], // to come back to redo this part.
      comments: [''],
    })
  }

  // validateToppings(toppingsArr: FormArray): ValidationErrors | null {
  //   const selectedToppings = toppingsArr.controls
  //     .filter(c => c.value)
  //     .length
  //   console.info(selectedToppings)
  
  //   if (selectedToppings === 0) {
  //     return { noToppingsSelected: true };
  //   }
  
  //   return null;
  // }

  onSubmit() {
    if (this.form.valid) {
      console.log(this.form.value)
      const data = this.form.value as PizzaDetails
      firstValueFrom(this.pizzaService.placeOrder(data)).then(
        v => {
          alert('Order Placed')
          this.router.navigate(['/orders', data.email])
          this.form.reset()
        })
        .catch(err => {
          alert(JSON.stringify(err))
        })
    }

  }
}
