import {Component, input} from '@angular/core';
import {Router} from "@angular/router";
import {RegistrationRequest} from "../../services/models/registration-request";
import {AuthenticationControllerService} from "../../services/services/authentication-controller.service";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {username: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationControllerService
  ) {
  }

  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    })
      .subscribe({
        next: () => {
          this.router.navigate(['login']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        }
      });
  }

  protected readonly input = input;
}
