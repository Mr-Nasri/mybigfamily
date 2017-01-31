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

    familyId : string = localStorage.getItem('currentFamily');
    city = '';
    members : Array<Member>;


    onSearchInCity(event: Event){
        event.preventDefault();
        this.familyService.searchInCity(this.familyId, this.city).subscribe(
            data => {this.members = data.json(); console.log(this.members)},
            err => { console.log('Error : ' + err) }
        );
    }

    findMailingList(event: Event){
        console.log("findMailingList");
    }
}