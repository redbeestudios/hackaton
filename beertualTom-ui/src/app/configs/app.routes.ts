
import { provideRouter, RouterConfig }  from '@angular/router';

import { EventsComponent } from '../components/events.cmpt';
import { EventComposeComponent } from '../components/eventCompose.cmpt';
import { EventDetailsComponent } from '../components/eventDetails.cmpt';

import { RestaurantsComponent } from '../components/restaurants.cmpt';
import { RestaurantComposeComponent } from '../components/restaurantCompose.cmpt';
import { RestaurantDetailsComponent } from '../components/restaurantDetails.cmpt';

const routes: RouterConfig = [
    {
        path: '',
        redirectTo: 'events',
    },
    {
        path: 'events',
        component: EventsComponent
    },
    {
        path: 'events/compose',
        component: EventComposeComponent
    },
    {
        path: 'events/:id',
        component: EventDetailsComponent
    },
    {
        path: 'restaurants',
        component: RestaurantsComponent
    },
    {
        path: 'restaurants/compose',
        component: RestaurantComposeComponent
    },
    {
        path: 'restaurants/:id',
        component: RestaurantDetailsComponent
    }
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
