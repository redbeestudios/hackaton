import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule }   from '@angular/router';


import { AppComponent } from './app.component';
import { EventComponent } from './components/event.cmpt';
import { EventCardComponent } from './components/event-card.cmpt';
import { EventDetailComponent } from './components/event.detail.cmpt';
import { HomeComponent } from './components/home.cmpt';
import { RestaurantComponent } from './components/restaurant.cmpt';
import { RestaurantCardComponent } from './components/restaurant-card.cmpt';
import { RestaurantDetailComponent } from './components/restaurant.detail.cmpt';

@NgModule({

  declarations: [
    AppComponent,
    EventComponent,
    EventCardComponent,
    EventDetailComponent,
    RestaurantComponent,
    RestaurantDetailComponent,
    RestaurantCardComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      { path: '', component: HomeComponent },
      { path: 'events', component: EventComponent },
      { path: 'events/:id', component: EventDetailComponent },
      { path: 'restaurants', component: RestaurantComponent },
      { path: 'restaurants/:id', component: RestaurantDetailComponent }
      //{ path: '**', component: RestaurantComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
