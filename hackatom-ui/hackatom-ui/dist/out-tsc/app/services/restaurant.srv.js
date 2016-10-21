var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';
var RestaurantService = (function () {
    function RestaurantService(http) {
        this.http = http;
        this.restaurantUrl = '/restaurants';
    }
    RestaurantService.prototype.getRestaurants = function () {
        return null;
    };
    RestaurantService.prototype.getRestaurant = function (id) {
        return this.http.get(this.restaurantUrl)
            .map(this.extractData)
            .catch(this.handleError);
    };
    RestaurantService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    RestaurantService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    };
    RestaurantService.prototype.saveRestaurants = function (restaurant) { };
    RestaurantService.prototype.deleteRestaurant = function (id) { };
    RestaurantService.prototype.editRestaurant = function (id) { };
    return RestaurantService;
}());
RestaurantService = __decorate([
    Injectable(),
    __metadata("design:paramtypes", [Http])
], RestaurantService);
export { RestaurantService };
//# sourceMappingURL=../../../../src/app/services/restaurant.srv.js.map