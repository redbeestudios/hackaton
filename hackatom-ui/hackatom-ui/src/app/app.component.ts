import { Component } from '@angular/core';
import { RestaurantComponent } from './components/restaurant.cmpt';


@Component({
  selector: 'app-root',
  template: `
  <h1>
    {{title}}
  </h1>
  <restaurant-cmpt></restaurant-cmpt>
  `,
  styles: [``]
})
export class AppComponent {
  title = 'app works!';
}
