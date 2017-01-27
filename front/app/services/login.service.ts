
import {EventEmitter} from "@angular/core";
export class LoginService {
    localStorageChange: EventEmitter<string> = new EventEmitter();
    constructor() {}

    connected : string;

    login(familyId : string){
        localStorage.setItem('currentFamily', familyId);
        this.connected = familyId;
        this.emitConnected(this.connected);
    }

    logout(){
        localStorage.removeItem('currentFamily');
        this.connected = '';
        this.emitConnected(this.connected);
    }
    emitConnected(connected : string) {
        this.localStorageChange.emit(connected);
    }

    getLoginChangeEmitter() {
        return this.localStorageChange;
    }
}