export var Event = (function () {
    function Event() {
    }
    Event.prototype.setId = function (id) {
        this.id = id;
    };
    Event.prototype.setUser = function (user) {
        this.user = user;
    };
    Event.prototype.setDish = function (dish) {
        this.dish = dish;
    };
    return Event;
}());
//# sourceMappingURL=../../../../src/app/domain/order.js.map