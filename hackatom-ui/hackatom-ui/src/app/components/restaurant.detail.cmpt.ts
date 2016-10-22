import { Component, OnInit } from '@angular/core';
import { Restaurant }  from '../domain/restaurant';
import { Dish }  from '../domain/dish';
import { RestaurantService } from '../services/restaurant.srv';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

@Component({
    selector: 'restaurant-detail-cmpt',
    template: `
    <div class="container">
      <h1>
        Restaurants
      </h1>
      <form #restaurantForm="ngForm" (ngSubmit)="submit()">
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" 
            [(ngModel)]="restaurant.name" class="form-control" id="name" placeholder="Metele el nombre..."
            name="name"
            #name="ngModel">
          </div>
          
          <div class="form-group">
            <label for="name">Agregar menú</label>
            
            <input type="text"  [(ngModel)]="dish.name"
             name="name"
            #name="ngModel"
             class="form-control" placeholder="Metele un plato..." name="dish">
             
             <button (click)="addDish()">
                Agregar
                </button>
          </div>
          
          <div *ngFor="let dish of restaurant.dishes" class="card">
            <span>{{dish.name}}</span>
          </div>
          
          <button type="submit" class="btn btn-default">Submit</button>
      </form>
   </div>   
  `,
    styles: [``],
    providers: [ RestaurantService ]
})
export class RestaurantDetailComponent implements OnInit {

    restaurant: Restaurant;
    dish: Dish;

    constructor(private restaurantService:  RestaurantService,
                private route: ActivatedRoute,
                private location: Location)
    {}

    ngOnInit() {

        this.restaurant = new Restaurant();

        /*this.route.params.forEach((params: Params) => {

             let id = params['id'];

             this.restaurantService.get(id)
                 .subscribe((restaurant) => this.restaurant = restaurant);
        });*/
    }

    submit() {
        console.log(this.restaurant.name)
    }

    addDish() {
        this.restaurant.dishes.push(this.dish);
    }

}
