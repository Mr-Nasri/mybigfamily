import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'create-family',
    templateUrl: 'app/components/search.component.html',
})
export class SearchComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {

    }

    familyName = '';
    creator = new Member();
    genderValues = ['M', 'F'];
    familyId : string = localStorage.getItem('currentFamily');
    city = '';

    onSearchInCity(event: Event){
        event.preventDefault();
        console.log(JSON.stringify(this.creator));
        var family = new Family();
        family.creator = this.creator;
        family.name = this.familyName;

        this.familyService.searchInCity(this.familyId, this.city).subscribe(
            data => {this.familyId = data.text()},
            err => { console.log('Error : ' + err) }
        );
    }

    findMailingList(event: Event){
        console.log("findMailingList");
    }
}