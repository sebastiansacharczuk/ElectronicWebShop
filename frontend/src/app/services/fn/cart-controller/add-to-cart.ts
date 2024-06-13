/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { ProductResponse } from '../../models/product-response';

export interface AddToCart$Params {
  productId: number;
  quantity: number;
}

export function addToCart(http: HttpClient, rootUrl: string, params: AddToCart$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ProductResponse>>> {
  const rb = new RequestBuilder(rootUrl, addToCart.PATH, 'post');
  if (params) {
    rb.query('productId', params.productId, {});
    rb.query('quantity', params.quantity, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<ProductResponse>>;
    })
  );
}

addToCart.PATH = '/cart/add';
