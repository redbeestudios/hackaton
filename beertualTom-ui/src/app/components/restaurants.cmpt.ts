import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';

@Component({
  selector: 'restaurants',
  template:
    `<div>
      <p>Restaurants</p>
    </div>
    `,
  styles:[`

  `]
})
export class RestaurantsComponent implements OnInit{

  constructor() {
  }

  ngOnInit(){
    //this.getUser();
  }

}
