import {Component, EventEmitter, Input, Output} from '@angular/core';
import { ProductResponse } from '../../../../services/models/product-response';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent {
  private _product: ProductResponse = {} as ProductResponse;
  @Output() addToCart = new EventEmitter<number>(); // Emit productId when adding to cart

  onAddToCart() {
    this.addToCart.emit(this.product.id);
  }
  @Input()
  set product(value: ProductResponse) {
    this._product = value;
  }

  get product(): ProductResponse {
    return this._product;
  }

  get productImage(): string {
    // If the product has an image, return the image URL.
    // Otherwise, return a placeholder URL.
    return this._product.image ? this._product.image : 'https://picsum.photos/2000/2000';
  }


}
