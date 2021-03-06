"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
require("../../rxjs-extensions");
var DeliveryFormComponent = (function () {
    function DeliveryFormComponent(router) {
        this.router = router;
        this.delivery = {};
    }
    DeliveryFormComponent.prototype.ngOnInit = function () {
        console.log('Delivery Form');
    };
    DeliveryFormComponent.prototype.onSubmit = function () {
        console.log(this.delivery);
    };
    DeliveryFormComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/delivery/view/form.html'
        })
    ], DeliveryFormComponent);
    return DeliveryFormComponent;
}());
exports.DeliveryFormComponent = DeliveryFormComponent;
//# sourceMappingURL=form.component.js.map