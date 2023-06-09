export interface PizzaDetails{
    name: string
    email: string
    size: string
    base: string
    sauce: string
    toppings: string[]
    comments: string
}

export interface Order{
    date:string
    email:string
    orderId: string
    total: number
}