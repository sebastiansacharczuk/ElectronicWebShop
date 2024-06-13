/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addOrUpdateProduct } from '../fn/product-controller/add-or-update-product';
import { AddOrUpdateProduct$Params } from '../fn/product-controller/add-or-update-product';
import { getAllProducts } from '../fn/product-controller/get-all-products';
import { GetAllProducts$Params } from '../fn/product-controller/get-all-products';
import { getProductById } from '../fn/product-controller/get-product-by-id';
import { GetProductById$Params } from '../fn/product-controller/get-product-by-id';
import { PageResponseProductResponse } from '../models/page-response-product-response';
import { ProductResponse } from '../models/product-response';

@Injectable({ providedIn: 'root' })
export class ProductControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addOrUpdateProduct()` */
  static readonly AddOrUpdateProductPath = '/product/add';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addOrUpdateProduct()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addOrUpdateProduct$Response(params: AddOrUpdateProduct$Params, context?: HttpContext): Observable<StrictHttpResponse<ProductResponse>> {
    return addOrUpdateProduct(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addOrUpdateProduct$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addOrUpdateProduct(params: AddOrUpdateProduct$Params, context?: HttpContext): Observable<ProductResponse> {
    return this.addOrUpdateProduct$Response(params, context).pipe(
      map((r: StrictHttpResponse<ProductResponse>): ProductResponse => r.body)
    );
  }

  /** Path part for operation `getProductById()` */
  static readonly GetProductByIdPath = '/product/{productId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getProductById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProductById$Response(params: GetProductById$Params, context?: HttpContext): Observable<StrictHttpResponse<ProductResponse>> {
    return getProductById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getProductById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getProductById(params: GetProductById$Params, context?: HttpContext): Observable<ProductResponse> {
    return this.getProductById$Response(params, context).pipe(
      map((r: StrictHttpResponse<ProductResponse>): ProductResponse => r.body)
    );
  }

  /** Path part for operation `getAllProducts()` */
  static readonly GetAllProductsPath = '/product/all';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllProducts()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllProducts$Response(params?: GetAllProducts$Params, context?: HttpContext): Observable<StrictHttpResponse<PageResponseProductResponse>> {
    return getAllProducts(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllProducts$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllProducts(params?: GetAllProducts$Params, context?: HttpContext): Observable<PageResponseProductResponse> {
    return this.getAllProducts$Response(params, context).pipe(
      map((r: StrictHttpResponse<PageResponseProductResponse>): PageResponseProductResponse => r.body)
    );
  }

}
