import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'get-family',
    templateUrl: 'app/components/getFamily.component.html',
})
export class GetFamilyComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {

    }

    familyId = '';
    result = '';

    onSubmit(event: Event){
        event.preventDefault();
        console.log(this.familyId);

        this.familyService.getFamily(this.familyId).subscribe(
            data => {this.result = data.text()},
            err => { console.log('Error : ' + err) }
        );

    }
}