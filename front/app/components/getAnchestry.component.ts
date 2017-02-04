

import {LoginService} from "../services/login.service";
import {Component} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import { AnchestryService }  from '../services/anchestry.service';

@Component({
    selector: 'get-anchestry',
    templateUrl: 'app/components/getAnchestry.component.html',
})
export class GetAnchestryComponent  {
    constructor(private route: ActivatedRoute, private router: Router , private loginService: LoginService, private anchestryService : AnchestryService) {

    }


}/**
 * Created by ahmed on 30/01/17.
 */
