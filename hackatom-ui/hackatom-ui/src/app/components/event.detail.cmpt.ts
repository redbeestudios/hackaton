import { Component, OnInit } from '@angular/core';
import { Event }  from '../domain/event';
import { Restaurant }  from '../domain/restaurant';
import { ActivatedRoute, Params } from '@angular/router';
import {Location, DatePipe} from '@angular/common';

@Component({
    selector: 'event-detail-cmpt',
    template: `
    <div class="container">
      <h1>
        Eventos
      </h1>
      <form #eventForm="ngForm" (ngSubmit)="submit()">
          <div class="form-group">
            <label for="date">Fecha del evento</label>
            <input type="date" 
            [(ngModel)]="event.date" class="form-control" id="date" placeholder="Cuando queres hacer el evento?"
            name="date"
            #date="ngModel">
          </div>
          
          <div class="form-group">
                <div class="input-group">
                    <input type="text"  [(ngModel)]="restaurant.name"
                     name="name"
                     #name="ngModel"
                     class="form-control" placeholder="Agrega un restaurant..." name="restaurant">
                  
                  <span class="input-group-btn">
                    <button (click)="addRestaurant()" class="btn btn-default">Agregar</button>
                  </span>
                </div><!-- /input-group -->
          </div>
          
          <h3>Platos</h3>
          
          <ul>
            <li *ngFor="let restaurant of event.restaurants">
                <span>{{restaurant.name}}</span>
            </li>
          </ul>
          
          
          <button type="submit" class="btn btn-default">Submit</button>
      </form>
   </div>   
  `,
    styles: [``]
})
export class EventDetailComponent implements OnInit {

    event: Event;
    restaurant: Restaurant;

    constructor(private route: ActivatedRoute,
                private location: Location)
    {}

    ngOnInit() {

        this.route.params.forEach((params: Params) => {

            this.restaurant = new Restaurant('','', []);

            let id = params['id'];

            if (id !== 'new') {

            } else {
                this.event = new Event('new',new Date());

            }
        });
    }

    submit() {

        console.log(this.event.date)
    }

    addRestaurant() {

        if (this.restaurant.name && this.restaurant.name.length > 0) {

            this.event.restaurants.push(this.restaurant);
            this.restaurant = new Restaurant('', '', []);
        }
    }

}
