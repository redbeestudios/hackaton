import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';

@Component({
  selector: 'events',
  template:
    `<div>
      <p>Eventos</p>
    </div>
    `,
  styles:[`

  `]
})
export class EventsComponent implements OnInit{

  constructor() {
  }

  ngOnInit(){
    //this.getUser();
  }

}
