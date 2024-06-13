import {Component, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {AuthService} from "../../../../services/authToken/auth.service";
import {AsyncPipe, NgIf} from "@angular/common";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [
    RouterLink,
    NgIf,
    AsyncPipe
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  isLoggedIn: boolean = false

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  logout() {
    this.authService.clearToken();
    this.isLoggedIn = false;
    location.reload();
  }
}
