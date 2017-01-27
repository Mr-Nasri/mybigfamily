import { HomeComponent } from './components/home.component';
import { CreateFamilyComponent } from './components/createFamily.component';
import { GetFamilyComponent } from './components/getFamily.component';
import { AboutComponent } from './components/about.component';
import {AddMemberComponent} from "./components/addMember.component";
import {AuthGuard} from "./guards/auth.guard";


export const Routes = [
    { path: '', component: HomeComponent },
    { path: 'create', component: CreateFamilyComponent },
    { path: 'getFamily', component: GetFamilyComponent },
    { path: 'about', component: AboutComponent },
    { path: 'addMember', component: AddMemberComponent, canActivate: [AuthGuard] },
];