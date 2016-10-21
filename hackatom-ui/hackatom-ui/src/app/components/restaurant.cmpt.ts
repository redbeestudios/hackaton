import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantService } from '../services/restaurant.srv';

@Component({
  selector: 'restaurant-cmpt',
  template: `
    <div *ngFor="let restaurant of restaurants">
      {{  restaurant.id }}
    </div>
  `,
  styles: [``],
  providers: [ RestaurantService ]
})
export class RestaurantComponent implements OnInit {

  restaurants: Array<Restaurant>;

  constructor(private restaurantService:  RestaurantService) {}

  ngOnInit() {
    this.restaurants = this.getRestaurants();
  }

  getRestaurants(): Array<Restaurant> {
    let restaurants = [ {id: '2', name: 'petalo', dishes: [{ name: 'muzza', type: 'pizza' }]} ];
    //return this.restaurantService.getRestaurants();
    return restaurants;
  }

}
