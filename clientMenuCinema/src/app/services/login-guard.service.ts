import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { LoginService } from './login.service';

@Injectable()
export class LoginGuard implements CanActivate{

    constructor(private log:LoginService, private router:Router ){}

    canActivate(route : ActivatedRouteSnapshot, state: RouterStateSnapshot ): Observable <boolean> | Promise<boolean> | boolean {
       if(this.log.isAuth()){
            return true;
        } else{
            this.router.navigate(['/login'])
        }
    }
}