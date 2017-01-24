"use strict";
var home_component_1 = require('./components/home.component');
var createFamily_component_1 = require('./components/createFamily.component');
var getFamily_component_1 = require('./components/getFamily.component');
var about_component_1 = require('./components/about.component');
exports.Routes = [
    { path: '', component: home_component_1.HomeComponent },
    { path: 'create', component: createFamily_component_1.CreateFamilyComponent },
    { path: 'getFamily', component: getFamily_component_1.GetFamilyComponent },
    { path: 'about', component: about_component_1.AboutComponent }
];
//# sourceMappingURL=app.routes.js.map