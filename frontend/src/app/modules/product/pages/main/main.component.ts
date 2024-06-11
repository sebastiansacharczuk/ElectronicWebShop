import { Component } from '@angular/core';
import {ProductCatalogComponent} from "../product-catalog/product-catalog.component";
import {MenuComponent} from "../../component/menu/menu.component";

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    ProductCatalogComponent,
    MenuComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {

}
