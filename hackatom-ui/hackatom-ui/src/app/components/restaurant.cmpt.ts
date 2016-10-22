import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantCardComponent } from '../components/restaurant-card.cmpt';
import { RestaurantService } from '../services/restaurant.srv';
import { Router } from '@angular/router';

@Component({
  selector: 'restaurant-cmpt',
  template: `
    <div class="container">
<<<<<<< HEAD
      <h1>
        Restaurants
      </h1>

      <button type="button" class="btn btn-success block" styles="text-align:right" (click)="gotoRestaurant({id:'new'})">Crear</button>
      <br><br>
      <div  *ngFor="let restaurant of restaurants"class="card">
        <div class="card-block">
          <h4 class="card-title">{{ restaurant.id }} - {{ restaurant.name }}</h4>
          <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
          <button class="btn btn-primary" (click)="gotoRestaurant(restaurant)">Ver</button>
          <a href="#" class="btn btn-danger">Borrar</a>
=======
      <span  class="title"> Restaurants</span>
      <a routerLink="/restaurant/new" class="btn btn-success block" style="float: right; margin-top: 1%; margin-right: 23px;">Crear</a>
      <br><br>
      <div *ngFor="let restaurant of restaurants" class="card">
          <restaurant-card [restaurant]="restaurant"></restaurant-card>
      </div>
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <h4 class="modal-title" id="myModalLabel">Eliminar Restaurante</h4>
            </div>
            <div class="modal-body">
              Estas seguro que deas borrar este restaurant?
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
              <button type="button" class="btn btn-danger">Eliminar</button>
            </div>
          </div>
>>>>>>> 4c90e193935bb416b47281272daabcda9935923e
        </div>
      </div>

    </div>
  `,
  styles: [`
    .title {
      font-size: 36px;
    }
    `],
  providers: [ RestaurantService ]
})
export class RestaurantComponent implements OnInit {

  restaurants: Array<Restaurant>;

  constructor(private restaurantService:  RestaurantService,
              private router: Router) {}

  ngOnInit() {
     this.getRestaurants();
  }

<<<<<<< HEAD
  gotoRestaurant(restaurant) {

      let link = ['/restaurants', restaurant.id];
      this.router.navigate(link);

  }

  getRestaurants(): Array<Restaurant> {
    let restaurants = [ {id: '2', name: 'Petalo', dishes: [{ name: 'muzza', type: 'pizza' }]},
       {id: '3', name: 'Hamburgo', dishes: [{ name: 'muzza', type: 'pizza' }]}
   ];
    //return this.restaurantService.getRestaurants();
    return restaurants;
  }
=======
  getRestaurants() {
    let self = this;
    this.restaurantService.getRestaurants().subscribe( res => {
      self.restaurants = res;
    });
    // return restaurants;
    }
>>>>>>> 4c90e193935bb416b47281272daabcda9935923e

}
