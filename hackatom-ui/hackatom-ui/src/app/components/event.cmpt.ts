import { Component } from '@angular/core';

@Component({
  selector: 'event-cmpt',
  template:`
  <div class="container">
    <button type="button" class="btn btn-success block" styles="text-align:right" (click)="gotoEvent({id:'new'})">Crear</button>
    <a type="button" class="btn btn-success block" styles="text-align:right">Crear</a>
    <br><br>
    <div *ngFor="let event of events" class="card">
        <event-card [event]="event"></event-card>
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
  styles: [``]
})
export class EventComponent {
  events: Array<Event> = [];

  constructor() {}

}
