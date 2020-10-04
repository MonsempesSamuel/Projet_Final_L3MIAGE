import { Component, OnInit, Input } from '@angular/core';
import { Plat, PlatCommande } from 'src/types';
import { PlatService } from '../services/platService.service';

@Component({
  selector: 'app-plat',
  templateUrl: './plat.component.html',
  styleUrls: ['./plat.component.scss']
})
export class PlatComponent implements OnInit {
  @Input() plat:Plat;
  @Input() position:boolean;  //Vrai => position gauche faux position droite
  constructor(private p:PlatService) {
  }

  ngOnInit(): void {
  }

  getId():String{
    return this.plat.id;
  }
  getNom():String{
    return this.plat.nom;
  }
  getPrix():String{
    return this.plat.prix;
  }
  getUrl():String{
    return this.plat.imageUrl;
  }
  getType():String{
    return this.plat.type;
  }
  getIngredients():String[]{
    return this.plat.ingredients;
  }

  dernierIngredient(i:number){
    return this.plat.ingredients.length-1 == i;
  }
  
  setCommandeOn(){
    this.p.setCommandeOn(this.plat);
  }
  laBonneCOmmande():boolean{
    return this.p.laBonneCOmmande(this.plat);
  }
  aucuneCommandeOn():boolean{
    return this.p.aucuneCommandeOn();
  }
  valideCommande($event:PlatCommande){
    this.p.valideCommande($event);
  }



}