import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";
import {LoginService} from "../services/login.service";
import { AnchestryService }  from '../services/anchestry.service';
import {Response} from "@angular/http";

@Component({
    selector: 'get-by-name',
    templateUrl: 'app/components/getByName.component.html',
})
export class GetByNameComponent  {



    constructor(private route: ActivatedRoute, private router: Router,
                private familyService : FamilyService, private loginService: LoginService, private anchestryService: AnchestryService) {
                }

    name = '';
    danger = '';
    result = '';
    city = '';
    membersInCity : Array<Member>;
    find(event: Event){
        event.preventDefault();

        console.log(this.name);

        this.anchestryService.getAnchestreByName( this.name).subscribe(

            data => {this.membersInCity = data.json(); console.log(this.membersInCity)},

            err => { console.log('Error : ' + err) }
            //console.log(result);

        );

    }

    private clone(object: any){
        // hack
        return JSON.parse(JSON.stringify(object));
    }


}/**
 * Created by user on 02/02/17.
 */
