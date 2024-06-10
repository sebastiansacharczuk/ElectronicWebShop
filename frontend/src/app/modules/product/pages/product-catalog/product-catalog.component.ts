import {Component, OnInit} from '@angular/core';
import {ProductControllerService} from "../../../../services/services/product-controller.service";
import {Router} from "@angular/router";
import {PageResponseProductResponse} from "../../../../services/models/page-response-product-response";
import {NgForOf} from "@angular/common";
import { ProductResponse } from '../../../../services/models';

@Component({
  selector: 'app-product-catalog',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './product-catalog.component.html',
  styleUrl: './product-catalog.component.scss'
})
export class ProductCatalogComponent implements OnInit {
  productResponse : PageResponseProductResponse = {}
  page = 0
  size = 5
  constructor(
    private productService: ProductControllerService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllProducts()
  }
  private findAllProducts() {
    this.productService.getAllProducts({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (products) => {
        console.log(products.content)
        this.productResponse = products;

        
      },
      error: err => console.log(err)
    })
  }

}

