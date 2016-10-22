var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from '@angular/core';
import { Restaurant } from '../domain/restaurant';
export var RestaurantCardComponent = (function () {
    function RestaurantCardComponent() {
    }
    __decorate([
        Input(), 
        __metadata('design:type', Restaurant)
    ], RestaurantCardComponent.prototype, "restaurant", void 0);
    RestaurantCardComponent = __decorate([
        Component({
            selector: 'restaurant-card',
            template: "\n  <div class=\"card-block\">\n    <span>\n      <span class=\"card-title\">{{ restaurant.id }} - {{ restaurant.name }}</span>\n      <div style=\"float: right\">\n        <button class=\"btn btn-primary block\">Editar</button>\n        <button class=\"btn btn-danger block\" data-toggle=\"modal\" data-target=\"#myModal\">Borrar</button>\n      </div>\n    </span>\n  </div>\n  ",
            styles: [""]
        }), 
        __metadata('design:paramtypes', [])
    ], RestaurantCardComponent);
    return RestaurantCardComponent;
}());
//# sourceMappingURL=../../../../src/app/components/restaurant-card.cmpt.js.map