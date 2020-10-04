import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class MenuCinemaView {



    constructor(private router:Router,private l:LoginService){}

    
    clickFilm(){
        //this.choixFilm=true;
        this.router.navigate(['/film'])
    }
    
    clickRepas(){
        this.router.navigate(['/menu'])
    }

    retourMenu(){
        this.router.navigate([''])
    }

    validationCommande(){
        this.l.setTrueValideCommande();
        this.router.navigate(['/validation_commande'])
    }
}