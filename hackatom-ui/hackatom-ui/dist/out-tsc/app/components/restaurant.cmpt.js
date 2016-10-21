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
var RestaurantComponent = (function () {
    function RestaurantComponent(restaurantService) {
        this.restaurantService = restaurantService;
    }
    RestaurantComponent.prototype.ngOnInit = function () {
        this.getRestaurants();
    };
    RestaurantComponent.prototype.getRestaurants = function () {
        var self = this;
        this.restaurantService.getRestaurants().subscribe(function (res) {
            self.restaurants = res;
        });
    };
    return RestaurantComponent;
}());
RestaurantComponent = __decorate([
    Component({
        selector: 'restaurant-cmpt',
        template: "\n    <div class=\"container\">\n      <div  *ngFor=\"let restaurant of restaurants\"class=\"card\">\n        <div class=\"card-block\">\n          <h4 class=\"card-title\">{{ restaurant.id }} - {{ restaurant.name }}</h4>\n          <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>\n          <a href=\"#\" class=\"btn btn-danger\">Borrar</a>\n        </div>\n      </div>\n    </div>\n  ",
        styles: [""],
        providers: [RestaurantService]
    }),
    __metadata("design:paramtypes", [RestaurantService])
], RestaurantComponent);
export { RestaurantComponent };
//# sourceMappingURL=../../../../src/app/components/restaurant.cmpt.js.map