import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Plat, PlatCommande } from 'src/types';
import { PanierServiceService } from '../services/panier-service.service';
import { PlatService } from '../services/platService.service';

@Component({
  selector: 'app-affichage-plat',
  templateUrl: './affichage-plat.component.html',
  styleUrls: ['./affichage-plat.component.scss']
})

export class AffichagePlatComponent implements OnInit {
  private idPlatCommande:number=0;
  tabPlat:Plat[];

  constructor(private httpClient: HttpClient,private panier:PanierServiceService,private plats:PlatService) { 
    this.tabPlat=this.plats.getTableauPlat();      
  }
  
  ngOnInit(): void {}

// On affecter a id_commande la valeur de l'id du plat
  setCommandeOn(plat:Plat){
    this.panier.setEntrainDeSaisir(true);
    this.idPlatCommande=+plat.id;
  }
  laBonneCOmmande(plat:Plat):boolean{
    return (this.panier.getEntrainDeSaisir())&&(+plat.id===this.idPlatCommande);
  }
  aucuneCommandeOn():boolean{
    return !this.panier.getEntrainDeSaisir();
  }
  valideCommande($event:PlatCommande){
    //this.tableauCommande.emit($event);
    this.idPlatCommande=0;
  }
  getId(plat:Plat):String{
    return plat.id;
  }
  getNom(plat:Plat):String{
    return plat.nom;
  }
  getPrix(plat:Plat):String{
    return plat.prix;
  }
  getType(plat:Plat):String{
    return plat.type;
  }
  getIngredients(plat:Plat):String[]{
    return plat.ingredients;
  }
  getUrlImagePlat(p:Plat){
    return p.imageUrl;
  }
}
