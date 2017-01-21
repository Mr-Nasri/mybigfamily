import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'header',
    templateUrl: 'app/components/header.component.html',
})
export class HeaderComponent  {
    constructor(private route: ActivatedRoute) {

    }
    name = 'Angular';
}