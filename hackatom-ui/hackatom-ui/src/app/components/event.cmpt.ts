import { Component, OnInit } from '@angular/core';
import { Event }  from '../domain/event';
import { EventCardComponent } from '../components/event-card.cmpt';
import { Router } from '@angular/router';

@Component({
    selector: 'event-cmpt',
    template: `
    <div class="container">
      <span  class="title"> Eventos</span>
      <button (click)="gotoEvent({id:'new'})" class="btn btn-success block" style="float: right; margin-top: 1%; margin-right: 23px;">Crear</button>
      <br><br>
      <div *ngFor="let event of events" class="card">
          <event-card [event]="event" (click)="gotoEvent(event)"></event-card>
      </div>
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <h4 class="modal-title" id="myModalLabel">Eliminar Evento</h4>
            </div>
            <div class="modal-body">
              Estas seguro que deas borrar este evento?
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
    `]
})
export class EventComponent implements OnInit {

    events: Array<Event>;

    constructor(private router: Router) {}

    ngOnInit() {
        this.getEvents();
    }


    gotoEvent(event) {

        let link = ['/events', event.id];
        this.router.navigate(link);

    }

    getEvents() {
        let self = this;
        self.events = [new Event('1', new Date())];

        // return restaurants;
    }

}
