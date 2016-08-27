import { Component, OnInit } from '@angular/core';
import { AppSettings } from '../configs/app.settings';
import { ROUTER_DIRECTIVES } from '@angular/router';


@Component({
  selector: 'events',
  template:
    `<div class="ui-container">
      <p>este es el compose</p>
    </div>
    `,
  styles:[`
    .ui-container {
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
      min-height: 200px;
    }

    .create-event-button {
      padding: 10px;
      border: solid 1px;
      border-radius: 5px;
      border-color: #A4A4A4;
      padding: 10px;
      background-color: red;
     }
  `],
  directives: [ ROUTER_DIRECTIVES ]
})
export class EventDetailsComponent implements OnInit{

  constructor() {
  }

  ngOnInit(){
    //this.getUser();
  }

}
