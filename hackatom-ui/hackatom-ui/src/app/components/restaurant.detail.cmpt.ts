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
      <form #restaurantForm="ngForm" (ngSubmit)="submit()" *ngIf="restaurant">
          <div class="form-group">
            <label for="name">Name</label>
            <input type="text" 
            [(ngModel)]="restaurant.name" class="form-control" id="name" placeholder="Metele el nombre..."
            name="name"
            #name="ngModel">
          </div>
          
          <div class="form-group">
                <div class="input-group">
                    <input type="text"  [(ngModel)]="dish.name"
                     name="name"
                     #name="ngModel"
                     class="form-control" placeholder="Metele un plato..." name="dish">
                  
                  <span class="input-group-btn">
                    <button (click)="addDish()" class="btn btn-default">Agregar</button>
                  </span>
                </div><!-- /input-group -->
          </div>
          
          <h3>Platos</h3>
          
          <ul>
            <li *ngFor="let dish of restaurant.dishes">
                <span>{{dish.name}}</span>
            </li>
          </ul>
          
          
          <button type="submit" class="btn btn-default">Submit</button>
      </form>
   </div>   
  `,
    styles: [``],
    providers: [ RestaurantService ]
})
export class RestaurantDetailComponent implements OnInit {

    restaurant: Restaurant
    dish: Dish;

    constructor(private restaurantService:  RestaurantService,
                private route: ActivatedRoute,
                private location: Location)
    {}

    ngOnInit() {

        this.route.params.forEach((params: Params) => {

            this.dish = new Dish();

            let id = params['id'];

            if (id !== 'new') {
                this.restaurantService.get(id)
                    .subscribe((restaurant) => {
                        console.log('sdjaksdj '+restaurant);
                        this.restaurant = restaurant
                    });
            } else {
                this.restaurant = new Restaurant('','', []);
            }
        });
    }

    submit() {

        console.log(this.restaurant.name)
    }

    addDish() {

        if (this.dish.name && this.dish.name.length > 0) {
            let duplicatedDishes = this.restaurant.dishes.filter((storedDish) => {
                return storedDish.name === this.dish.name
            })
            if (duplicatedDishes.length === 0) {
                this.restaurant.dishes.push(this.dish);
                this.dish = new Dish();
            }
        }
    }

}
