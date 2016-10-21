import { Injectable } from '@angular/core';
import { Restaurant } from '../domain/restaurant';


@Injectable()
export class RestaurantService {

  getRestaurants(): Array<Restaurant> {
    return null;
  }

  saveRestaurants(restaurant: Restaurant): void {}

  deleteRestaurant(id: string): void {}

  editRestaurant(id: string): void {}

}
