
import { provideRouter, RouterConfig }  from '@angular/router';
import { EventsComponent } from '../components/events.cmpt';
import { RestaurantsComponent } from '../components/restaurants.cmpt';

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
        path: 'restaurants',
        component: RestaurantsComponent
    }
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];
