import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule }   from '@angular/router';


import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.cmpt';
import { RestaurantComponent } from './components/restaurant.cmpt';
import { RestaurantCardComponent } from './components/restaurant-card.cmpt';
import { RestaurantDetailComponent } from './components/restaurant.detail.cmpt';

@NgModule({

  declarations: [
    AppComponent,
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
      { path: 'restaurants', component: RestaurantComponent },
      { path: 'restaurants/:id', component: RestaurantDetailComponent }
      //{ path: '**', component: RestaurantComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
