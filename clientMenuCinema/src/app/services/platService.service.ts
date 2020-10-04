import { Injectable } from "@angular/core";
import { Plat, PlatCommande } from 'src/types';
import { PanierServiceService } from './panier-service.service';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class PlatService{
    saisiCommandeEnCours:boolean;
    id_commande:number=0;
    tableauPlat:Plat[]=[];

    constructor(private panier:PanierServiceService,private http: HttpClient){
      
    }

  async getPlatFromServeur(){
    const responseServeur = await this.POST('/api/plat',{});
    console.log('post :', responseServeur.body);
    this.traduireXML(responseServeur.body)
  }

  traduireXML(text){
    this.tableauPlat=[]
    let parser = new DOMParser();
    let xmlPlat = parser.parseFromString(text, "application/xml");
    const plats = xmlPlat.getElementsByTagName("plat");
    for(let i=0;i<plats.length;i++){
      let ingredients = plats.item(i).getElementsByTagName("ingredientPlat");
      let tabIngredients:string[]=[];
      for(let j = 0; j < ingredients.length; j++){
        tabIngredients.push(ingredients.item(j).innerHTML);
      }
      let plat:Plat={
        id : plats.item(i).getElementsByTagName("idPlat").item(0).innerHTML,
        nom: plats.item(i).getElementsByTagName("nomPlat").item(0).innerHTML,
        prix:plats.item(i).getElementsByTagName("prixPlat").item(0).innerHTML,
        imageUrl:plats.item(i).getElementsByTagName("urlImagePlat").item(0).innerHTML,
        type:plats.item(i).getElementsByTagName("typePlat").item(0).innerHTML,
        ingredients:tabIngredients
      }
      this.tableauPlat.push(plat);
    }
  }

  getTableauPlat():Plat[]{
    this.getPlatFromServeur();
    return this.tableauPlat;
  }

  // On affecter a id_commande la valeur de l'id du plat
  setCommandeOn(plat:Plat){
    this.panier.setEntrainDeSaisir(true);
    this.id_commande=+plat.id;
  }
  laBonneCOmmande(plat:Plat):boolean{
    return (this.panier.getEntrainDeSaisir())&&(+plat.id===this.id_commande);
  }
  aucuneCommandeOn():boolean{
    return !this.panier.getEntrainDeSaisir();
  }
  valideCommande($event:PlatCommande){
    //this.tableauCommande.emit($event);
    this.id_commande=0;
  }

  private POST(url: string, params: {[key: string]: string}): Promise<HttpResponse<string>> {
    const P = new HttpParams( {fromObject: params} );
    return this.http.post( url, P, {
    observe: 'response',
    responseType: 'text',
    headers: {'content-type': 'application/x-www-form-urlencoded'}
    }).toPromise();
}

  

}