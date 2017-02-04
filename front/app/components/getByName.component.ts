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
    find(event: Event){
        event.preventDefault();

        console.log(this.name);

        this.anchestryService.getAnchestreByName( this.name).subscribe(
            (res: Response) => {

                this.result = JSON.stringify(res);

                console.log(this.result);
                if(this.result === ''){
                    this.danger  = 'Sorry Person doesn\'t exist in the KD';
                }else{
                    this.danger = '';
                }
            },
            //console.log(result);
            err => { console.log('Error : ' + err) }
        );

    }

    private clone(object: any){
        // hack
        return JSON.parse(JSON.stringify(object));
    }


}/**
 * Created by user on 02/02/17.
 */
