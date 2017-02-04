import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { RouterModule }   from '@angular/router';
import { HttpModule, JsonpModule } from '@angular/http';

import {APP_BASE_HREF, CommonModule} from '@angular/common';

import { AppComponent }  from './app.component';
import { Routes } from './app.routes';

import { HomeComponent } from './components/home.component';
import { CreateFamilyComponent } from './components/createFamily.component';
import { GetFamilyComponent } from './components/getFamily.component';
import { AboutComponent } from './components/about.component';
import {SearchComponent} from "./components/search.component";
import { HeaderComponent } from './components/header.component';

import { GetByNameComponent } from './components/getByName.component';
import { GetCommonComponent } from './components/getCommon.component';
import { GetAnchestryComponent } from './components/getAnchestry.component';

import { AnchestryService }          from './services/anchestry.service';
import { FamilyService }          from './services/family.service';
import {LoginService} from "./services/login.service";

import {AddMemberComponent} from "./components/addMember.component";
import {AuthGuard} from "./guards/auth.guard";
import {KeysPipe} from "./pipes/keys.pipe";



@NgModule({
  imports:      [ BrowserModule, CommonModule, FormsModule, HttpModule, JsonpModule, RouterModule.forRoot(Routes) ],
  declarations: [ AppComponent, HomeComponent, CreateFamilyComponent, GetFamilyComponent, SearchComponent, AboutComponent, HeaderComponent,
                  AddMemberComponent, KeysPipe, GetAnchestryComponent, GetCommonComponent, GetByNameComponent ],
  bootstrap:    [ AppComponent ],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }, FamilyService, LoginService, AnchestryService, AuthGuard]
})
export class AppModule { }
