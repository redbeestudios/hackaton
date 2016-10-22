import { Component, Input } from '@angular/core';
import { Restaurant } from '../domain/restaurant';

@Component({
  selector: 'restaurant-card',
  template: `
  <div class="card-block">
    <span>
      <span class="card-title">{{ restaurant.id }} - {{ restaurant.name }}</span>
      <div style="float: right">
        <button class="btn btn-primary block">Editar</button>
        <button class="btn btn-danger block" data-toggle="modal" data-target="#myModal">Borrar</button>
      </div>
    </span>
  </div>
  `,
  styles: [``]
})
export class RestaurantCardComponent {

  @Input()
  restaurant: Restaurant;
}
