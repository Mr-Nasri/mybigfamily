import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import {FamilyService} from "../services/family.service";
import {Member} from "../models/Member";
import {Family} from "../models/Family";

@Component({
    selector: 'create-family',
    templateUrl: 'app/components/addMember.component.html',
})
export class AddMemberComponent  {
    constructor(private route: ActivatedRoute, private familyService : FamilyService) {
        this.familyService.getMembers(this.familyId).subscribe(
            data => {this.members = data.json(); console.log(data.json())},
            err => { console.log('Error : ' + err) }
        );
    }

    relations = ['child', 'parent', 'sibling', 'spouse'];
    genderValues = ['M', 'F'];
    members : any;

    member = new Member();

    familyId : string = localStorage.getItem('currentFamily');

    success = '';

    onSubmit(event: Event){
        event.preventDefault();
        this.familyService.addMember(this.member, this.familyId).subscribe(
            data => {this.success = data.text()},
            err => { console.log('Error : ' + err) }
        );
    }
}