import { Component } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  template:
    `<div class="content col-md-10 col-md-offset-1">
        <router-outlet></router-outlet>
    </div>

      `,
  styleUrls: ['app.component.css']
})
export class AppComponent {
  title = 'BeerTualTom';
}
