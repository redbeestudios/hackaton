import { Component } from '@angular/core';
import { NavbarComponent } from './components/navbar.cmpt';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  template:
    `<div class="content col-md-10 col-md-offset-1">
        <navbar></navbar>
        <router-outlet></router-outlet>
    </div>

      `,
  styleUrls: ['app.component.css'],
  directives: [ NavbarComponent ]
})
export class AppComponent {
  title = 'BeerTualTom';
}
