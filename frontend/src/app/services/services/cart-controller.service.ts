/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addToCart } from '../fn/cart-controller/add-to-cart';
import { AddToCart$Params } from '../fn/cart-controller/add-to-cart';
import { getCart } from '../fn/cart-controller/get-cart';
import { GetCart$Params } from '../fn/cart-controller/get-cart';
import { ProductResponse } from '../models/product-response';

@Injectable({ providedIn: 'root' })
export class CartControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addToCart()` */
  static readonly AddToCartPath = '/cart/add';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addToCart()` instead.
   *
   * This method doesn't expect any request body.
   */
  addToCart$Response(params: AddToCart$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ProductResponse>>> {
    return addToCart(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addToCart$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  addToCart(params: AddToCart$Params, context?: HttpContext): Observable<Array<ProductResponse>> {
    return this.addToCart$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ProductResponse>>): Array<ProductResponse> => r.body)
    );
  }

  /** Path part for operation `getCart()` */
  static readonly GetCartPath = '/cart';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getCart()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCart$Response(params?: GetCart$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ProductResponse>>> {
    return getCart(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getCart$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getCart(params?: GetCart$Params, context?: HttpContext): Observable<Array<ProductResponse>> {
    return this.getCart$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ProductResponse>>): Array<ProductResponse> => r.body)
    );
  }

}
