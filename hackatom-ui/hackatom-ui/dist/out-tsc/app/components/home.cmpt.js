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
export var HomeComponent = (function () {
    function HomeComponent() {
    }
    HomeComponent = __decorate([
        Component({
            selector: 'selector-cmpt',
            template: "\n    <div class=\"container\">\n      <div class=\"col-md-6\">\n        <a routerLink=\"/restaurants\" class=\"btn btn-success block\" styles=\"text-align:right\">Restaurants</a>\n      </div>\n      <div class=\"col-md-6\">\n        <a routerLink=\"/restaurants\" type=\"button\" class=\"btn btn-success block\" styles=\"text-align:right\">Evento</a>\n      </div>\n        <router-outlet></router-outlet>\n    </div>\n  ",
            styles: [""]
        }), 
        __metadata('design:paramtypes', [])
    ], HomeComponent);
    return HomeComponent;
}());
//# sourceMappingURL=../../../../src/app/components/home.cmpt.js.map