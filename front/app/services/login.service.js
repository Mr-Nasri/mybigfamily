"use strict";
var core_1 = require("@angular/core");
var LoginService = (function () {
    function LoginService() {
        this.localStorageChange = new core_1.EventEmitter();
    }
    LoginService.prototype.login = function (familyId) {
        localStorage.setItem('currentFamily', familyId);
        this.connected = familyId;
        this.emitConnected(this.connected);
    };
    LoginService.prototype.logout = function () {
        localStorage.removeItem('currentFamily');
        this.connected = '';
        this.emitConnected(this.connected);
    };
    LoginService.prototype.emitConnected = function (connected) {
        this.localStorageChange.emit(connected);
    };
    LoginService.prototype.getLoginChangeEmitter = function () {
        return this.localStorageChange;
    };
    return LoginService;
}());
exports.LoginService = LoginService;
//# sourceMappingURL=login.service.js.map