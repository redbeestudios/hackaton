var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component } from '@angular/core';
import { RestaurantService } from '../services/restaurant.srv';
export var RestaurantComponent = (function () {
    function RestaurantComponent(restaurantService) {
        this.restaurantService = restaurantService;
    }
    RestaurantComponent.prototype.ngOnInit = function () {
        this.restaurants = this.getRestaurants();
    };
    RestaurantComponent.prototype.getRestaurants = function () {
        var restaurants = [{ id: '2', name: 'Petalo', dishes: [{ name: 'muzza', type: 'pizza' }] },
            { id: '3', name: 'Hamburgo', dishes: [{ name: 'muzza', type: 'pizza' }] }
        ];
        return restaurants;
    };
    RestaurantComponent = __decorate([
        Component({
            selector: 'restaurant-cmpt',
            template: "\n    <div class=\"container\">\n      <h1>\n        Restaurants\n      </h1>\n      <a routerLink=\"/restaurant/new\" type=\"button\" class=\"btn btn-success block\" styles=\"text-align:right\">Crear</a>\n      <br><br>\n      <div *ngFor=\"let restaurant of restaurants\"class=\"card\">\n        <div class=\"card-block\">\n          <span>\n            <span class=\"card-title\">{{ restaurant.id }} - {{ restaurant.name }}</span>\n            <div style=\"float: right\">\n              <a href=\"#\" class=\"btn btn-primary block\">Editar</a>\n              <a href=\"#\" class=\"btn btn-danger block\">Borrar</a>\n            </div>\n          </span>\n        </div>\n      </div>\n    </div>\n  ",
            styles: [""],
            providers: [RestaurantService]
        }), 
        __metadata('design:paramtypes', [RestaurantService])
    ], RestaurantComponent);
    return RestaurantComponent;
}());
//# sourceMappingURL=../../../../src/app/components/restaurant.cmpt.js.map