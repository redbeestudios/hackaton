import { Component } from '@angular/core';

@Component({
  selector: 'selector-cmpt',
  template: `
    <div class="container">
      <div class="col-md-6 padding-tam-1">
        <a routerLink="/restaurants" class="btn btn-success block tom-button">Restaurants</a>
      </div>
      <div class="col-md-6 padding-tam-1">
        <a routerLink="/restaurants" class="btn btn-success block tom-button">Evento</a>
      </div>
        <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .padding-tam-1{
      padding-top: 40%;
    }
    .tom-button {
      position:  absolute;
      left: 20%;
      top: 50%;
      min-height: 100px;
      min-width: 300px;

      text-align: center;
      font-size: 18px;

      padding-top: 6% !important;
    }

    `]
})
export class HomeComponent {


}
