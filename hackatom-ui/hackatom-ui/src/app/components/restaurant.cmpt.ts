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
      <a routerLink="/restaurant/new" type="button" class="btn btn-success block" styles="text-align:right">Crear</a>
      <br><br>
      <div *ngFor="let restaurant of restaurants"class="card">
        <div class="card-block">
          <span>
            <span class="card-title">{{ restaurant.id }} - {{ restaurant.name }}</span>
            <div style="float: right">
              <a href="#" class="btn btn-primary block">Editar</a>
              <a href="#" class="btn btn-danger block">Borrar</a>
            </div>
          </span>
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
