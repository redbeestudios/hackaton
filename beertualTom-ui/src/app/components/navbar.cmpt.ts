import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';

@Component({
  selector: 'navbar',
  template:
    `<nav class='navbar navbar-default'>
    <div class="container-fluid">

    <div class="navbar-header">
      <a class="navbar-brand">BeerTual Tom</a>
    </div>

    <ul class="nav navbar-nav navbar-right nav-bar-buttons">
      <li class="button-navbar"><button type="submit" class="btn btn-default">Restaurants</button></li>
      <li class="button-navbar"><button type="submit" class="btn btn-default">Eventos</button></li>
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
      padding-top: 5px;
     }

     .logout-button{
        position: relative;
        top: 5px;
        color: #000;
     }

     .logout-button:hover{
        cursor: pointer;
     }

  `]
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
