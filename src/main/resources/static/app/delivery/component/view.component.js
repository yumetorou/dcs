"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
require("../../rxjs-extensions");
var DeliveryViewComponent = (function () {
    function DeliveryViewComponent(router) {
        this.router = router;
    }
    DeliveryViewComponent.prototype.ngOnInit = function () {
        console.log('Customer View');
    };
    DeliveryViewComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/delivery/view/view.html'
        })
    ], DeliveryViewComponent);
    return DeliveryViewComponent;
}());
exports.DeliveryViewComponent = DeliveryViewComponent;
//# sourceMappingURL=view.component.js.map