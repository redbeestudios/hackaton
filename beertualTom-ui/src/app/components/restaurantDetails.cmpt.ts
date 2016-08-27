import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';

@Component({
  selector: 'restaurants',
  template:
    `<div class="ui-container">
      <p>Restaurants</p>
    </div>
    `,
  styles:[`
    .ui-container {
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
      min-height: 200px;
    }
  `]
})
export class RestaurantDetailsComponent implements OnInit{

  constructor() {
  }

  ngOnInit(){
    //this.getUser();
  }

}
