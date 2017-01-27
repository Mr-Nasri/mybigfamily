import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {LoginService} from "./services/login.service";

@Component({
  selector: 'my-app',
  templateUrl: 'app/app.component.html',
})
export class AppComponent  {
  constructor( private route: ActivatedRoute, private loginService: LoginService) {

  }
  name = 'Angular';
  loginSubscription: any;
  connected : string = localStorage.getItem('currentFamily');

  ngOnInit() {
    this.loginSubscription = this.loginService.getLoginChangeEmitter()
        .subscribe((item : string )=> this.setConnected(item));
  }
  setConnected(connected : string) {
    this.connected = connected;
  }
  ngOnDestroy() {
    this.loginSubscription.unsubscribe();
  }
}
