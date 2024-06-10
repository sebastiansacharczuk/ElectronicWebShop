import { Component } from '@angular/core';
import {ProductCatalogComponent} from "../product-catalog/product-catalog.component";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    ProductCatalogComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {

}
