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
        this.familyService.getMembers(this.familyId).subscribe(
            data => {this.members = data.json(); console.log(data.json())},
            err => { console.log('Error : ' + err) }
        );
    }
    members : any;
    familyId : string = localStorage.getItem('currentFamily');


    city = '';
    membersInCity : Array<Member>;
    onSearchInCity(event: Event){
        event.preventDefault();
        this.familyService.searchInCity(this.familyId, this.city).subscribe(
            data => {this.membersInCity = data.json(); console.log(this.membersInCity)},
            err => { console.log('Error : ' + err) }
        );
    }

    person : string;
    relatives : any;
    findRelatives(event: Event){
        event.preventDefault();
        this.familyService.findRelatives(this.familyId, this.person).subscribe(
            data => {this.relatives = data.json(); console.log(this.relatives)},
            err => { console.log('Error : ' + err) }
        );
    }
}