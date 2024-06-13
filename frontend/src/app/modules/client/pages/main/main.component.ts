import { Component } from '@angular/core';
import { ClientInfoComponent } from '../client-info/client-info.component';
import { ClientCartComponent } from '../client-cart/client-cart.component';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [
    ClientInfoComponent,
    ClientCartComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.scss'
})
export class MainComponent {

}
