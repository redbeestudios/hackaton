import { Injectable } from '@angular/core';
import { Restaurant } from '../domain/restaurant';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class RestaurantService {
  private restaurantUrl = '/restaurants';

  constructor(private http: Http) {}

  getRestaurants(): Array<Restaurant> {
    return null;
  }

  getRestaurant(id: String): Observable<Restaurant> {

    return this.http.get(this.restaurantUrl)
                    .map(this.extractData)
                    .catch(this.handleError);

  }
  private extractData(res: Response) {
    let body = res.json();

    return body.data || {};
  }

  private handleError (error: any) {
      // In a real world app, we might use a remote logging infrastructure
      // We'd also dig deeper into the error to get a better message
      let errMsg = (error.message) ? error.message :
        error.status ? `${error.status} - ${error.statusText}` : 'Server error';
      console.error(errMsg); // log to console instead
      return Observable.throw(errMsg);
    }

  saveRestaurants(restaurant: Restaurant): void {}

  deleteRestaurant(id: string): void {}

  editRestaurant(id: string): void {}

}
