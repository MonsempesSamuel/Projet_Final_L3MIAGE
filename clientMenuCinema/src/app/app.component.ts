import { Component } from '@angular/core';
import { MenuCinemaView } from './services/menuCinemaView.service';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private mcv:MenuCinemaView, private log:LoginService){}

  clickFilm(){
    this.mcv.clickFilm()
  }
  clickRepas(){
    this.mcv.clickRepas();
  }
  clickMenu(){
    this.mcv.retourMenu();
  }

  connexionGoogle(){
    this.log.loginGoogle();
  }
  isConnected(){
    return this.log.isAuth();
  }
  deconnexion(){
    this.log.signOutUser();
  }



}