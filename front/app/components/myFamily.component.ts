import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'my-family',
    templateUrl: 'app/components/myFamily.component.html',
})
export class MyFamilyComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {
        this.familyService.getAllMembers(this.familyId).subscribe(
            data => {this.members = data.json(); console.log(data.json())},
            err => { console.log('Error : ' + err) }
        );
    }
    members : any;
    familyId : string = localStorage.getItem('currentFamily');
}