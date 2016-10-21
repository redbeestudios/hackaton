import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { RestaurantService } from '../services/restaurant.srv';

@Component({
  selector: 'restaurant-cmpt',
  template: `
    <div class="container">
      <span  class="title"> Restaurants</span>
      <a routerLink="/restaurant/new" class="btn btn-success block" style="float: right">Crear</a>
      <br><br>
      <div *ngFor="let restaurant of restaurants"class="card">
        <div class="card-block">
          <span>
            <span class="card-title">{{ restaurant.id }} - {{ restaurant.name }}</span>
            <div style="float: right">
              <button class="btn btn-primary block">Editar</button>
              <button class="btn btn-danger block" data-toggle="modal" data-target="#myModal">Borrar</button>
            </div>
          </span>
        </div>
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
              Estas seguro que queres eliminar este restaurant?
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
