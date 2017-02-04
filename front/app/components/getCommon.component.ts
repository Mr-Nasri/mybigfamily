import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";
import {LoginService} from "../services/login.service";
import { AnchestryService }  from '../services/anchestry.service';
import {Response} from "@angular/http";

@Component({
    selector: 'getCommon',
    templateUrl: 'app/components/getCommon.component.html',
})



export class GetCommonComponent  {
    constructor(private route: ActivatedRoute, private router: Router,
                private familyService : FamilyService, private loginService: LoginService, private anchestryService: AnchestryService) {




        this.anchestryService.getCommon().subscribe(
            (res: Response) => {
                this.result = res.json();
                console.log(this.result);
            },
            //console.log(result);
            err => { console.log('Error : ' + err) }
        );

    }

    result ='';
    private clone(object: any){
        // hack
        return JSON.parse(JSON.stringify(object));
    }
}/**
 * Created by user on 02/02/17.
 */
