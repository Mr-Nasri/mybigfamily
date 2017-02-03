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
var SearchComponent = (function () {
    function SearchComponent(route, familyService) {
        var _this = this;
        this.route = route;
        this.familyService = familyService;
        this.familyId = localStorage.getItem('currentFamily');
        this.city = '';
        this.familyService.getMembers(this.familyId).subscribe(function (data) { _this.members = data.json(); console.log(data.json()); }, function (err) { console.log('Error : ' + err); });
    }
    SearchComponent.prototype.onSearchInCity = function (event) {
        var _this = this;
        event.preventDefault();
        this.familyService.searchInCity(this.familyId, this.city).subscribe(function (data) { _this.membersInCity = data.json(); console.log(_this.membersInCity); }, function (err) { console.log('Error : ' + err); });
    };
    SearchComponent.prototype.findRelatives = function (event) {
        var _this = this;
        event.preventDefault();
        this.familyService.findRelatives(this.familyId, this.person).subscribe(function (data) { _this.relatives = data.json(); console.log(_this.relatives); }, function (err) { console.log('Error : ' + err); });
    };
    SearchComponent = __decorate([
        core_1.Component({
            selector: 'create-family',
            templateUrl: 'app/components/search.component.html',
        }), 
        __metadata('design:paramtypes', [router_1.ActivatedRoute, family_service_1.FamilyService])
    ], SearchComponent);
    return SearchComponent;
}());
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map