import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantService } from '../services/restaurant.srv';

@Component({
  selector:'restaurant-cmpt',
  template:`
  `,
  styles:[``],
  providers: [ RestaurantService ]
})
export class RestaurantComponent implements OnInit {

  constructor(private restaurantService:  RestaurantService) {}

  ngOnInit() {
    this.getRestaurants();
  }

  getRestaurants(): Array<Restaurant> {
    return this.restaurantService.getRestaurants();
  }

}
