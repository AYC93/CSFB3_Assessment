import { Component, OnInit, inject } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
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
  
  minToppings: ValidatorFn = (ctrl: AbstractControl): ValidationErrors | null => {
    const valid = (ctrl as FormArray).controls.some(c => c.value)
    return valid ? null : { minToppings: true }
  }

  createForm(): FormGroup {
    this.toppingsArr = this.fb.array(PIZZA_TOPPINGS.map(() => this.fb.control(false)));
    this.toppingsArr.setValidators(this.minToppings);

    return this.fb.group({
      name: this.fb.control<string>('Ang Yi Ci', [Validators.required]),
      email: this.fb.control<string>('ayc.leo@gmail.com', [Validators.required, Validators.email]),
      size: this.fb.control<number>(1, [Validators.required]),
      base: this.fb.control<string>('', [Validators.required]),
      sauce: this.fb.control<string>('', [Validators.required]),
      toppings: this.toppingsArr,
      comments: (''),
    })
  }
  


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




