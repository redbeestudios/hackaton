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
var EventService = (function () {
    function EventService(http) {
        this.http = http;
        this.eventUrl = '52.90.47.124:8081/events';
    }
    EventService.prototype.getEvents = function () {
        return this.http.get(this.eventUrl)
            .map(this.extractData)
            .catch(this.handleError);
    };
    EventService.prototype.getEvent = function (id) {
        return this.http.get(this.eventUrl + '/' + id)
            .map(this.extractData)
            .catch(this.handleError);
    };
    EventService.prototype.saveEvent = function (event) {
        var body = JSON.stringify(event);
        return this.http.post(this.eventUrl, body)
            .map(this.extractData)
            .catch(this.handleError);
    };
    EventService.prototype.updateEvent = function (event) {
        var body = JSON.stringify(event);
        return this.http.put(this.eventUrl + '/' + event.id, body)
            .map(this.extractData)
            .catch(this.handleError);
    };
    EventService.prototype.deleteEvent = function (id) {
        return this.http.delete(this.eventUrl + '/' + id)
            .map(this.extractData)
            .catch(this.handleError);
    };
    EventService.prototype.extractData = function (res) {
        var body = res.json();
        return body.data || {};
    };
    EventService.prototype.handleError = function (error) {
        var errMsg = (error.message) ? error.message :
            error.status ? error.status + " - " + error.statusText : 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    };
    return EventService;
}());
EventService = __decorate([
    Injectable(),
    __metadata("design:paramtypes", [Http])
], EventService);
export { EventService };
//# sourceMappingURL=../../../../src/app/services/event.srv.js.map