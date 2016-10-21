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
        this.getRestaurants();
    };
    RestaurantComponent.prototype.getRestaurants = function () {
        var self = this;
        this.restaurantService.getRestaurants().subscribe(function (res) {
            self.restaurants = res;
        });
    };
    RestaurantComponent = __decorate([
        Component({
            selector: 'restaurant-cmpt',
            template: "\n    <div class=\"container\">\n      <span  class=\"title\"> Restaurants</span>\n      <a routerLink=\"/restaurant/new\" class=\"btn btn-success block\" style=\"float: right\">Crear</a>\n      <br><br>\n      <div *ngFor=\"let restaurant of restaurants\"class=\"card\">\n        <div class=\"card-block\">\n          <span>\n            <span class=\"card-title\">{{ restaurant.id }} - {{ restaurant.name }}</span>\n            <div style=\"float: right\">\n              <button class=\"btn btn-primary block\">Editar</button>\n              <button class=\"btn btn-danger block\" data-toggle=\"modal\" data-target=\"#myModal\">Borrar</button>\n            </div>\n          </span>\n        </div>\n      </div>\n\n      <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n        <div class=\"modal-dialog\" role=\"document\">\n          <div class=\"modal-content\">\n            <div class=\"modal-header\">\n              <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n                <span aria-hidden=\"true\">&times;</span>\n              </button>\n              <h4 class=\"modal-title\" id=\"myModalLabel\">Eliminar Restaurante</h4>\n            </div>\n            <div class=\"modal-body\">\n              Estas seguro que queres eliminar este restaurant?\n            </div>\n            <div class=\"modal-footer\">\n              <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Cerrar</button>\n              <button type=\"button\" class=\"btn btn-danger\">Eliminar</button>\n            </div>\n          </div>\n        </div>\n      </div>\n\n    </div>\n  ",
            styles: ["\n    .title {\n      font-size: 36px;\n    }\n    "],
            providers: [RestaurantService]
        }), 
        __metadata('design:paramtypes', [RestaurantService])
    ], RestaurantComponent);
    return RestaurantComponent;
}());
//# sourceMappingURL=../../../../src/app/components/restaurant.cmpt.js.map