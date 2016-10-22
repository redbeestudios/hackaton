import { Component, Input } from '@angular/core';
import { Event } from '../domain/event';

@Component({
  selector: 'event-card',
  template: `
    <div class="card-block">
      <span class="card-title">{{ event.id }} - {{ event.name }}</span>
      <div style="float: right">
        <button class="btn btn-danger block" data-toggle="modal" data-target="#myModal">Borrar</button>
      </div>
    </div>
  `,
  styles: [``]
})
export class EventCardComponent {

  @Input()
  event: Event;
}
