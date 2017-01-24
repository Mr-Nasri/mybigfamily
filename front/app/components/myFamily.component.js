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
var family_service_1 = require("../services/family.service");
var Member_1 = require("../models/Member");
var Family_1 = require("../models/Family");
var MyFamilyComponent = (function () {
    function MyFamilyComponent(route, familyService) {
        this.route = route;
        this.familyService = familyService;
        this.familyName = '';
        this.creator = new Member_1.Member();
        this.genderValues = ['M', 'F'];
        this.familyId = null;
    }
    MyFamilyComponent.prototype.onSubmit = function (event) {
        var _this = this;
        event.preventDefault();
        console.log(JSON.stringify(this.creator));
        var family = new Family_1.Family();
        family.creator = this.creator;
        family.name = this.familyName;
        this.familyService.createFamily(family).subscribe(function (data) { _this.familyId = data.text(); }, function (err) { console.log('Error : ' + err); });
    };
    MyFamilyComponent = __decorate([
        core_1.Component({
            selector: 'my-family',
            templateUrl: 'app/components/myFamily.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, family_service_1.FamilyService])
    ], MyFamilyComponent);
    return MyFamilyComponent;
}());
exports.MyFamilyComponent = MyFamilyComponent;
//# sourceMappingURL=myFamily.component.js.map