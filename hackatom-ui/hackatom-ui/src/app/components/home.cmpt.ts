import { Component } from '@angular/core';

@Component({
  selector: 'selector-cmpt',
  template: `
    <div class="container">
      <div class="col-md-6">
        <a routerLink="/restaurants" class="btn btn-success block" styles="text-align:right">Restaurants</a>
      </div>
      <div class="col-md-6">
        <a routerLink="/restaurants" type="button" class="btn btn-success block" styles="text-align:right">Evento</a>
      </div>
        <router-outlet></router-outlet>
    </div>
  `,
  styles: [``]
})
export class HomeComponent {



  // restaurants: Array<Restaurant>;
  //
  // constructor(private restaurantService:  RestaurantService) {}
  //
  // ngOnInit() {
  //   this.restaurants = this.getRestaurants();
  // }
  //
  // getRestaurants(): Array<Restaurant> {
  //   let restaurants = [ {id: '2', name: 'Petalo', dishes: [{ name: 'muzza', type: 'pizza' }]},
  //      {id: '3', name: 'Hamburgo', dishes: [{ name: 'muzza', type: 'pizza' }]}
  //  ];
  //   //return this.restaurantService.getRestaurants();
  //   return restaurants;
  // }

}
