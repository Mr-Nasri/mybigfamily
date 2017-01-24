import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';


@Component({
    selector: 'about',
    templateUrl: 'app/components/about.component.html',
})
export class AboutComponent  {
    constructor(private route: ActivatedRoute) {

    }
}