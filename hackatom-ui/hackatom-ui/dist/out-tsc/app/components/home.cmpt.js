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
            template: "\n    <div class=\"container\">\n      <div class=\"col-md-6 padding-tam-1\">\n        <a routerLink=\"/restaurants\" class=\"btn btn-success block tom-button\">Restaurants</a>\n      </div>\n      <div class=\"col-md-6 padding-tam-1\">\n        <a routerLink=\"/restaurants\" class=\"btn btn-success block tom-button\">Evento</a>\n      </div>\n        <router-outlet></router-outlet>\n    </div>\n  ",
            styles: ["\n    .padding-tam-1{\n      padding-top: 40%;\n    }\n    .tom-button {\n      position:  absolute;\n      left: 20%;\n      top: 50%;\n      min-height: 100px;\n      min-width: 300px;\n\n      text-align: center;\n      font-size: 18px;\n\n      padding-top: 6% !important;\n    }\n\n    "]
        }), 
        __metadata('design:paramtypes', [])
    ], HomeComponent);
    return HomeComponent;
}());
//# sourceMappingURL=../../../../src/app/components/home.cmpt.js.map