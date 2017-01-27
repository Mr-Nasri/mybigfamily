import {Component, Input, Output, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginService} from "../services/login.service";

@Component({
    selector: 'header',
    templateUrl: 'app/components/header.component.html',
})
export class HeaderComponent  {
    constructor(private route: ActivatedRoute, private router: Router, private loginService: LoginService) {

    }
    @Input() connected : string;
    @Output() connectedChange = new EventEmitter<string>();
    name = 'Angular';

    logout(){
        this.loginService.logout();
        this.router.navigate(['']);
    }
}