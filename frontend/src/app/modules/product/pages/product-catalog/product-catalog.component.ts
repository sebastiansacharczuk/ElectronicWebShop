import {Component, OnInit} from '@angular/core';
import {ProductControllerService} from "../../../../services/services/product-controller.service";
import {Router} from "@angular/router";
import {PageResponseProductResponse} from "../../../../services/models/page-response-product-response";
import {NgForOf} from "@angular/common";
import { ProductResponse } from '../../../../services/models';
import {ProductCardComponent} from "../../component/product-card/product-card.component";

@Component({
  selector: 'app-product-catalog',
  standalone: true,
  imports: [
    NgForOf,
    ProductCardComponent
  ],
  templateUrl: './product-catalog.component.html',
  styleUrl: './product-catalog.component.scss'
})
export class ProductCatalogComponent implements OnInit {
  productResponse : PageResponseProductResponse = {}
  page = 0
  size = 10
  pages: any = []
  constructor(
    private productService: ProductControllerService,
    private router: Router
  ) {
  }

  get isLastPage() {
    return this.page === this.productResponse.totalPages as number - 1;
  }

  ngOnInit(): void {
    this.findAllProducts()
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllProducts();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllProducts();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllProducts();
  }

  goToLastPage() {
    this.page = this.productResponse.totalPages as number - 1;
    this.findAllProducts();
  }

  goToNextPage() {
    this.page++;
    this.findAllProducts();
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

