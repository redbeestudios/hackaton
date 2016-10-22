import { Injectable } from '@angular/core';
import { Restaurant } from '../domain/restaurant';
import { Event } from '../domain/event';
import { Http, Response} from '@angular/http';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class EventService {
  private eventUrl = 'localhost/events';

  constructor(private http: Http) {}

  getEvents(): Observable<Event[]> {
    return this.http.get(this.eventUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getEvent(id: String): Observable<Event> {

    return this.http.get(this.eventUrl + '/' + id)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  saveEvent(event: Event): Observable<Event> {
    let body = JSON.stringify(event);
    return this.http.post(this.eventUrl, body)
      .map(this.extractData)
      .catch(this.handleError);
  }

  updateEvent(event: Event): Observable<Event> {
    let body = JSON.stringify(event);
    return this.http.put(this.eventUrl + '/' + event.id, body)
      .map(this.extractData)
      .catch(this.handleError);
  }

  deleteEvent(id: String): Observable<Response> {
    return this.http.delete(this.eventUrl + '/' + id)
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
