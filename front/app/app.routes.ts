import { HomeComponent } from './components/home.component';
import { CreateFamilyComponent } from './components/createFamily.component';
import { GetFamilyComponent } from './components/getFamily.component';
import { AboutComponent } from './components/about.component';
import {AddMemberComponent} from "./components/addMember.component";
import {AuthGuard} from "./guards/auth.guard";
import {SearchComponent} from "./components/search.component";
import {MyFamilyComponent} from "./components/myFamily.component";
var getByName_component_1 = require("./components/getByName.component");
var getCommon_component_1 = require("./components/getCommon.component");
var getAnchestry_component_1 = require("./components/getAnchestry.component");


export const Routes = [
    { path: '', component: HomeComponent },
    { path: 'create', component: CreateFamilyComponent },
    { path: 'getFamily', component: GetFamilyComponent },
    { path: 'about', component: AboutComponent },
    { path: 'anchestry', component: getAnchestry_component_1.GetAnchestryComponent },
    { path: 'getAnchestryByName', component: getByName_component_1.GetByNameComponent },
    { path: 'getCommon', component: getCommon_component_1.GetCommonComponent },
    { path: 'addMember', component: AddMemberComponent, canActivate: [AuthGuard] },
    { path: 'search', component: SearchComponent, canActivate: [AuthGuard] },
    { path: 'myFamily', component: MyFamilyComponent, canActivate: [AuthGuard] },

];