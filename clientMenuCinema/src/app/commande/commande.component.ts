import { Component, OnInit, Input } from '@angular/core';
import { Plat } from 'src/types';
import { PanierServiceService } from '../services/panier-service.service';

@Component({
  selector: 'app-commande',
  templateUrl: './commande.component.html',
  styleUrls: ['./commande.component.scss']
})
export class CommandeComponent implements OnInit {
  @Input() plat:Plat;
  nombre : number;
  //@Output() plat_quantite: EventEmitter<PlatCommande> = new EventEmitter();
  
  ngOnInit(): void {
    this.nombre=this.getQuantitePlat()+1
  }

  constructor(private panier:PanierServiceService) {
  }

  getQuantitePlat(){
    return this.panier.getPlatCommandeQuantite(+this.plat.id)?this.panier.getPlatCommandeQuantite(+this.plat.id):0
  }
  ajoute1(){
    this.nombre+=1;
  }
  eneleve1(){
    if(this.nombre>0){
      this.nombre-=1;      
    }
  }

  valideCommande(){
    this.panier.ajoutePlatPanier({
      id:this.plat.id,
      nom:this.plat.nom,
      prix:this.plat.prix,
      imageUrl:this.plat.imageUrl,
      type:this.plat.type,
      ingredients:this.plat.ingredients,
      quantite:this.nombre});
    this.panier.setEntrainDeSaisir(false);
  }
  
}
