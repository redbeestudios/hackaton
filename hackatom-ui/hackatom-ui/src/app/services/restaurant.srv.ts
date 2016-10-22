import { Injectable } from '@angular/core';
import { Restaurant } from '../domain/restaurant';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class RestaurantService {
  private restaurantUrl = '/restaurants';

  constructor(private http: Http) {}

  getRestaurants(): Observable<Restaurant[]> {
    let restaurant = new Restaurant('1', 'Petalo', [
  		{
  		"name": "Milanesa",
  		"type": "Plato Principal"
  		},
  		{
  		"name": "Creme Brulee",
  		"type": "Postre"
  		}
  	]);
    let restaurant2 = new Restaurant('2', 'Arepaepa', [
  		{
  		"name": "caca",
  		"type": "Plato Principal"
  		},
  		{
  		"name": "polenta",
  		"type": "Postre"
  		}
  	]);
    let restaurants = [];
    restaurants.push(restaurant);
    restaurants.push(restaurant2);
    let obs = Observable.of(restaurants);

    return obs;

    // return this.http.get(this.restaurantUrl)
                    // .map(this.extractData)
                    // .catch(this.handleError);
  }

  get(id: String): Observable<Restaurant> {

    /*return this.http.get(this.restaurantUrl + '/' + id)
                    .map(this.extractData)
                    .catch(this.handleError);*/

    console.log('idddd '+id);

    return Observable.of(new Restaurant());
  }

  saveRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    let body = JSON.stringify(restaurant);
    return this.http.post(this.restaurantUrl, body)
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    let body = JSON.stringify(restaurant);
    return this.http.put(this.restaurantUrl + '/' + restaurant.id, body)
      .map(this.extractData)
      .catch(this.handleError);
  }

  deleteRestaurant(id: String): Observable<Response> {
    return this.http.delete(this.restaurantUrl + '/' + id)
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

}
