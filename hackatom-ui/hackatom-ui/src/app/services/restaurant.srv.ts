import { Injectable } from '@angular/core';
import { Restaurant } from '../domain/restaurant';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class RestaurantService {
  private restaurantUrl = 'http://52.90.47.124:8081/restaurants';
  private restaurantsMock = {};

  constructor(private http: Http) {}

  restaurants(): Observable<Restaurant[]> {
    let getResult = this.http.get(this.restaurantUrl)
    console.log(getResult);
    return getResult.map(this.extractData)
                    .catch(this.handleError);
  }

  get(id: String): Observable<Restaurant> {

    return this.http.get(this.restaurantUrl + '/' + id)
                    .map(this.extractData)
                    .catch(this.handleError);

  }

  save(restaurant: Restaurant): Observable<Restaurant> {
    let body = JSON.stringify(restaurant);
    return this.http.post(this.restaurantUrl, body)
      .map(this.extractData);
      //.catch(this.handleError);
  }

  update(restaurant: Restaurant): Observable<Restaurant> {
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

    console.log(res);

    let body = res.json();

    console.log(''+ body);

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
