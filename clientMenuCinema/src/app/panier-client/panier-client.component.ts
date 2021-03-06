import { Component, OnInit } from '@angular/core';
import { PlatCommande, Plat } from 'src/types';
import { PanierServiceService } from '../services/panier-service.service';
import { MenuCinemaView } from '../services/menuCinemaView.service';
import { MovieResponse } from '../tmdb-data/Movie';


@Component({
  selector: 'app-panier-client',
  templateUrl: './panier-client.component.html',
  styleUrls: ['./panier-client.component.scss']
})


export class PanierClientComponent implements OnInit {

  constructor(private p:PanierServiceService, private mcv:MenuCinemaView) { 
  }


  enleveFilm(i:number){
    this.p.enleveFilmPanier(i);
  }
  getPlatPanier():PlatCommande[]{
    return this.p.getPlatPanier();
  }
  getMoviePanier():MovieResponse[]{
    return this.p.getMoviePanier();
  }

  getCout():number{
    return Math.round(this.p.getCout()*100)/100;
  }

  valideAfficheCout():boolean{
    return this.p.valideAfficheCout();
  }

  getNom(p:PlatCommande):String{
    return this.p.getNom(p);
  }
  getPrix(p:PlatCommande):number{
    return  Math.round(this.p.getPrix(p)*100)/100;
  }
  getQuantite(p:PlatCommande):number{
    return this.p.getQuantite(p);
  }

  taillePanierClient(){
    return this.p.taillePanier();
  }
  confirmePanier(){
    //console.log(this.p.getPlatPanier(),this.p.getMoviePanier());
    this.mcv.validationCommande();
  }

  ngOnInit(): void {
  }

  ajouteQuantite(pc:number){
    this.p.ajoutQuantite(pc);
    
  }

  enleveQuantite(pc:number){
    this.p.enleveQuantite(pc);
  }

  getNomFilm(film:MovieResponse)
  {
    return film.title ;
  }


}
