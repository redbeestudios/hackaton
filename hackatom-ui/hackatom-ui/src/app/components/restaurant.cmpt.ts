import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantCardComponent } from '../components/restaurant-card.cmpt';
import { RestaurantService } from '../services/restaurant.srv';
import { Router } from '@angular/router';

@Component({
  selector: 'restaurant-cmpt',
  template: `
    <div class="container">
      <span  class="title"> Restaurants</span>
      <a routerLink="/restaurant/new" class="btn btn-success block" style="float: right; margin-top: 1%; margin-right: 23px;">Crear</a>
      <br><br>
      <div *ngFor="let restaurant of restaurants" class="card">
          <restaurant-card [restaurant]="restaurant" (click)="gotoRestaurant(restaurant)"></restaurant-card>
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


  gotoRestaurant(restaurant) {

      let link = ['/restaurants', restaurant.id];
      this.router.navigate(link);

  }

  getRestaurants() {
    let self = this;
    this.restaurantService.getRestaurants().subscribe( res => {
      self.restaurants = res;
    });
    // return restaurants;
  }

}
