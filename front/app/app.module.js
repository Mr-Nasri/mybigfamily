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
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
var common_1 = require('@angular/common');
var app_component_1 = require('./app.component');
var app_routes_1 = require('./app.routes');
var home_component_1 = require('./components/home.component');
var createFamily_component_1 = require('./components/createFamily.component');
var getFamily_component_1 = require('./components/getFamily.component');
var about_component_1 = require('./components/about.component');
var header_component_1 = require('./components/header.component');
var family_service_1 = require('./services/family.service');
var login_service_1 = require("./services/login.service");
var addMember_component_1 = require("./components/addMember.component");
var auth_guard_1 = require("./guards/auth.guard");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [platform_browser_1.BrowserModule, common_1.CommonModule, forms_1.FormsModule, http_1.HttpModule, http_1.JsonpModule, router_1.RouterModule.forRoot(app_routes_1.Routes)],
            declarations: [app_component_1.AppComponent, home_component_1.HomeComponent, createFamily_component_1.CreateFamilyComponent, getFamily_component_1.GetFamilyComponent, about_component_1.AboutComponent, header_component_1.HeaderComponent,
                addMember_component_1.AddMemberComponent],
            bootstrap: [app_component_1.AppComponent],
            providers: [{ provide: common_1.APP_BASE_HREF, useValue: '/' }, family_service_1.FamilyService, login_service_1.LoginService, auth_guard_1.AuthGuard]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map