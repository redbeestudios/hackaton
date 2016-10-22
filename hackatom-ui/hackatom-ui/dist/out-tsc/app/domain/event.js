export var Event = (function () {
    function Event() {
        this.restaurants = [];
    }
    Event.prototype.setRestaurants = function (restaurants) {
        this.restaurants = restaurants;
    };
    Event.prototype.setDate = function (date) {
        this.date = date;
    };
    Event.prototype.setId = function (id) {
        this.id = id;
    };
    return Event;
}());
//# sourceMappingURL=../../../../src/app/domain/event.js.map