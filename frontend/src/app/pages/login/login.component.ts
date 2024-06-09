import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../services/models';
import { Router } from '@angular/router';
import { AuthenticationControllerService } from '../../services/services';
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {username: '', password: ''};
  errorMsg: Array<string> = [];
  constructor(
    private router: Router,
    private authService: AuthenticationControllerService,
    private tokenService: TokenService
  ) {
  }

  login() {
    this.errorMsg = [];
    this.authService.login({
      body: this.authRequest
    }).subscribe({
      next: (result) => {
        this.tokenService.token = result.token as string;
        this.router.navigate(['products']);
      },
      error: (err) => {
        console.log(err);
        if(err.status == 0){
          this.errorMsg.push("CONNECTION_ERROR");
        }
        if (err.error.validationErrors) {
          this.errorMsg = err.error.validationErrors;
        } else {
          this.errorMsg.push(err.error.error);
        }
        this.errorMsg
          .sort((a, b) => b.localeCompare(a))
          .sort((a, b) => a.length - b.length)
          ;
      }
    });
  }

  register() {
    this.router.navigate(['register']);
  }
}
