"use strict";
var home_component_1 = require('./components/home.component');
var createFamily_component_1 = require('./components/createFamily.component');
var getFamily_component_1 = require('./components/getFamily.component');
var about_component_1 = require('./components/about.component');
var addMember_component_1 = require("./components/addMember.component");
var auth_guard_1 = require("./guards/auth.guard");
var search_component_1 = require("./components/search.component");
var getByName_component_1 = require("./components/getByName.component");
var getCommon_component_1 = require("./components/getCommon.component");
var getAnchestry_component_1 = require("./components/getAnchestry.component");
exports.Routes = [
    { path: '', component: home_component_1.HomeComponent },
    { path: 'create', component: createFamily_component_1.CreateFamilyComponent },
    { path: 'getFamily', component: getFamily_component_1.GetFamilyComponent },
    { path: 'about', component: about_component_1.AboutComponent },
    { path: 'anchestry', component: getAnchestry_component_1.GetAnchestryComponent },
    { path: 'getAnchestryByName', component: getByName_component_1.GetByNameComponent },
    { path: 'getCommon', component: getCommon_component_1.GetCommonComponent },
    { path: 'addMember', component: addMember_component_1.AddMemberComponent, canActivate: [auth_guard_1.AuthGuard] },
    { path: 'search', component: search_component_1.SearchComponent, canActivate: [auth_guard_1.AuthGuard] },
];
//# sourceMappingURL=app.routes.js.map