import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';

export const routes: Routes = [
    {
      path: 'login',
      component: LoginComponent
    },
    {
      path: 'register',
      component: RegisterComponent
    },
    {
      path: '',
      loadChildren: () => import('./modules/product/product.module').then(m => m.ProductModule)
    },
    {
      path: 'product',
      loadChildren: () => import('./modules/product/product.module').then(m => m.ProductModule)
    },
    {
      path: 'client',
      loadChildren: () => import('./modules/client/client.module').then(m => m.ClientModule)
    }
];
