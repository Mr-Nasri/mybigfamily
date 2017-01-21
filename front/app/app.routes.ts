import { HomeComponent } from './components/home.component';
import { CreateFamilyComponent } from './components/createFamily.component';

export const Routes = [
    { path: '', component: HomeComponent },
    { path: 'create', component: CreateFamilyComponent }
];