import { Component, OnInit } from '@angular/core';
import { CartControllerService } from '../../../../services/services';
import { Router } from '@angular/router';
import { ProductResponse } from '../../../../services/models';
import { CartItemCardComponent } from "../../component/cart-item-card/cart-item-card.component";
import {NgForOf, NgIf} from "@angular/common";


@Component({
    selector: 'app-client-cart',
    standalone: true,
    templateUrl: './client-cart.component.html',
    styleUrl: './client-cart.component.scss',
  imports: [CartItemCardComponent, NgForOf, NgIf]
})
export class ClientCartComponent implements OnInit{
  cart: ProductResponse[] = []
  constructor(
    private cartService: CartControllerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getCart()
  }

  private getCart() {
    this.cartService.getCart().subscribe({
      next: (cart) => {
        console.log(cart)
        this.cart = cart;
      },
      error: err => console.log(err)
    })
  }
}
