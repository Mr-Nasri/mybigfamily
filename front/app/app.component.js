"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var login_service_1 = require("./services/login.service");
var AppComponent = (function () {
    function AppComponent(route, loginService) {
        this.route = route;
        this.loginService = loginService;
        this.name = 'Angular';
        this.connected = localStorage.getItem('currentFamily');
    }
    AppComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.loginSubscription = this.loginService.getLoginChangeEmitter()
            .subscribe(function (item) { return _this.setConnected(item); });
    };
    AppComponent.prototype.setConnected = function (connected) {
        this.connected = connected;
    };
    AppComponent.prototype.ngOnDestroy = function () {
        this.loginSubscription.unsubscribe();
    };
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            templateUrl: 'app/app.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, login_service_1.LoginService])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map