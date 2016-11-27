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
var InventoryListComponent = (function () {
    function InventoryListComponent(router, inventoryService) {
        this.router = router;
        this.inventoryService = inventoryService;
        this.data = [];
    }
    InventoryListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.inventoryService.getItems({ page: 0, size: 10 }).subscribe(function (result) {
            _this.data = result.results;
        });
        console.log('Inventory List');
    };
    InventoryListComponent = __decorate([
        core_1.Component({
            templateUrl: 'app/inventory/view/list.html',
            providers: [inventory_service_1.InventoryService]
        })
    ], InventoryListComponent);
    return InventoryListComponent;
}());
exports.InventoryListComponent = InventoryListComponent;
//# sourceMappingURL=list.component.js.map