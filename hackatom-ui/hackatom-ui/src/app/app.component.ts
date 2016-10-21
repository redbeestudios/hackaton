import { Component } from '@angular/core';
import { RestaurantComponent } from './components/restaurant.cmpt';


@Component({
  selector: 'app-root',
  template: `
  <br>
  <div class="container">
    <div class="card">
      <h1 style="text-align: center">
        {{title}}
      </h1>
    </div>
  </div>

  <!-- Routed views go here -->
  <router-outlet></router-outlet>
  `,
  styles: [``]
})
export class AppComponent {
  title = 'HackaTom';
}
