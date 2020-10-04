import { Component, OnInit } from '@angular/core';
import { PanierServiceService } from '../services/panier-service.service';
import { PlatCommande } from 'src/types';
import { MovieResponse } from '../tmdb-data/Movie';

@Component({
  selector: 'app-valide-panier',
  templateUrl: './valide-panier.component.html',
  styleUrls: ['./valide-panier.component.scss']
})
export class ValidePanierComponent implements OnInit {

  constructor(private p:PanierServiceService) { 
    }
  
    commandeEnvoye():boolean{
      return this.p.commandeEnvoye();
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

    adresseSaisi():boolean{
      return this.p.adresseSaisi()
    }

    confirmePanier(){
      console.log(this.p.getMoviePanier(),this.p.getPlatPanier())
      this.p.sendPanierToServeur()
      //const responseServeur= this.p.sendPanierToServeur();
    }
    ngOnInit(): void {
    }
    
  }
  