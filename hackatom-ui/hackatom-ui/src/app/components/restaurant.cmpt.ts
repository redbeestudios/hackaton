import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantService } from '../services/restaurant.srv';

@Component({
  selector: 'restaurant-cmpt',
  template: `
    <div class="container">
      <h1>
        Restaurants
      </h1>

      <button type="button" class="btn btn-success block" styles="text-align:right">Crear</button>
      <br><br>
      <div  *ngFor="let restaurant of restaurants"class="card">
        <div class="card-block">
          <h4 class="card-title">{{ restaurant.id }} - {{ restaurant.name }}</h4>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
          <a href="#" class="btn btn-primary">Ver</a>
          <a href="#" class="btn btn-danger">Borrar</a>
        </div>
      </div>
    </div>
  `,
  styles: [``],
  providers: [ RestaurantService ]
})
export class RestaurantComponent implements OnInit {

  restaurants: Array<Restaurant>;

  constructor(private restaurantService:  RestaurantService) {}

  ngOnInit() {
     this.getRestaurants();
  }

  getRestaurants() {
    let self = this;
    this.restaurantService.getRestaurants().subscribe( res => {
      self.restaurants = res;
    });
    // return restaurants;
    }

}
