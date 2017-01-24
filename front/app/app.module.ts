import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { RouterModule }   from '@angular/router';
import { HttpModule, JsonpModule } from '@angular/http';

import {APP_BASE_HREF} from '@angular/common';

import { AppComponent }  from './app.component';
import { Routes } from './app.routes';

import { HomeComponent } from './components/home.component';
import { CreateFamilyComponent } from './components/createFamily.component';
import { GetFamilyComponent } from './components/getFamily.component';
import { AboutComponent } from './components/about.component';
import { HeaderComponent } from './components/header.component';

import { FamilyService }          from './services/family.service';

@NgModule({
  imports:      [ BrowserModule, FormsModule, HttpModule, JsonpModule, RouterModule.forRoot(Routes) ],
  declarations: [ AppComponent, HomeComponent, CreateFamilyComponent, GetFamilyComponent, AboutComponent, HeaderComponent ],
  bootstrap:    [ AppComponent ],
  providers: [{provide: APP_BASE_HREF, useValue : '/' }, FamilyService]
})
export class AppModule { }
