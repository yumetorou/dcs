"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require("@angular/core");
require("../../rxjs-extensions");
var inventory_service_1 = require("../inventory.service");
var InventoryFormComponent = (function () {
    function InventoryFormComponent(router, inventoryService, route) {
        this.router = router;
        this.inventoryService = inventoryService;
        this.route = route;
        this.item = {};
    }
    InventoryFormComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            var id = +params['id'];
            if (id) {
                _this.inventoryService.getItem(id).subscribe(function (item) {
                    _this.item = item;
                });
            }
        });
        console.log('Inventory Form');
    };
    InventoryFormComponent.prototype.onSubmit = function () {
        this.inventoryService.saveItem(this.item).subscribe(function (result) {
            console.log('saved');
        }, function (error) {
            console.log('error');
        });
        console.log(this.item);
    };
    InventoryFormComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/inventory/view/form.html',
            providers: [inventory_service_1.InventoryService]
        })
    ], InventoryFormComponent);
    return InventoryFormComponent;
}());
exports.InventoryFormComponent = InventoryFormComponent;
//# sourceMappingURL=form.component.js.map