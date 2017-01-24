import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'my-family',
    templateUrl: 'app/components/getFamily.component.html',
})
export class GetFamilyComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {

    }

    familyId = '';

    onSubmit(event: Event){
        event.preventDefault();
        console.log(familyId);
        var family = new Family();
        family.creator = this.creator;
        family.name = this.familyName;

        this.familyService.createFamily(family).subscribe(
            data => {this.familyId = data.text()},
            err => { console.log('Error : ' + err) }
        );

    }
}