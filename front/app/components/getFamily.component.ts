import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";
import {LoginService} from "../services/login.service";

@Component({
    selector: 'get-family',
    templateUrl: 'app/components/getFamily.component.html',
})
export class GetFamilyComponent  {
    constructor(private route: ActivatedRoute, private router: Router,
                private familyService : FamilyService, private loginService: LoginService) {

    }

    familyId = '';
    result = '';

    onSubmit(event: Event){
        event.preventDefault();
        console.log(this.familyId);
        this.familyService.getFamily(this.familyId).subscribe(
            data => {
                this.result = data.text();
                if(this.result == "ok"){
                    console.log(this.result);
                    this.loginService.login(this.familyId);
                    this.router.navigate(['addMember']);
                } else {
                    this.result = "Family not found, please try again !";
                }
            },
            err => { console.log('Error : ' + err) }
        );

    }
}