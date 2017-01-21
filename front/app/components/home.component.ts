import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'create-family',
    templateUrl: 'app/components/home.component.html',
})
export class HomeComponent  {
    constructor(private route: ActivatedRoute) {

    }
    name = 'Angular';
}
