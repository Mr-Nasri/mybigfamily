import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'create-family',
    templateUrl: 'app/components/createFamily.component.html',
})
export class CreateFamilyComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {

    }

    familyName = '';
    creator = new Member();
    genderValues = ['M', 'F'];
    familyId : string = null;

    onSubmit(event: Event){
        event.preventDefault();
        console.log(JSON.stringify(this.creator));
        var family = new Family();
        family.creator = this.creator;
        family.name = this.familyName;

        this.familyService.createFamily(family).subscribe(
            data => {this.familyId = data.text()},
            err => { console.log('Error : ' + err) }
        );

    }
}
