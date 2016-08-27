import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';
import { ROUTER_DIRECTIVES } from '@angular/router';


@Component({
  selector: 'navbar',
  template:
    `<nav class='navbar navbar-default'>
    <div class="container-fluid">

    <div class="navbar-header">
      <a class="navbar-brand">BeerTual Tom</a>
    </div>

    <ul class="nav navbar-nav navbar-right nav-bar-buttons">
      <li class="button-navbar"><a routerLink='/events' routerLinkActive="active">Eventos</a></li>
      <li class="button-navbar"><a routerLink='/restaurants' routerLinkActive="active">Restaurants</a></li>
    </ul>

      </div>
    </nav>

    `,
  styles:[`
    .navbar {
      padding: 15px;
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
      margin-top: 1%;
    }

    .button-navbar {
      margin-left: 10px;
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
     }

     .button-navbar:hover {
       background-color: #FFFFFF;
      }

  `],
  directives: [ ROUTER_DIRECTIVES ]
})
export class NavbarComponent implements OnInit{

  constructor() {
  }

  ngOnInit(){
    //this.getUser();
  }

  // logout(){
  //   //TODO: mover a un service de uath
  //   Cookie.delete('Carsa-Token');
  //   Cookie.delete('Token-Type');
  //   Cookie.delete('Token-Expires-In');
  //   Cookie.delete('Token-Scope');
  //   Cookie.delete('JSESSIONID');
  //   window.location.replace('http://54.152.31.87:18000/logout?redirect_uri=http://localhost:4200/');
  // }
}
