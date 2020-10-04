import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { PlatCommande, Plat } from 'src/types';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { LoginService } from './login.service';
import { MovieResponse } from '../tmdb-data/Movie';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class PanierServiceService {
  private cmdEnvoye:boolean=false;
  private cmdEnvoye$:BehaviorSubject<boolean>= new BehaviorSubject(this.cmdEnvoye); 
  private ListeFilmCommande:MovieResponse[];
  private ListePlatCommande:PlatCommande[];
  private cout:number;
  private entrainDeSaisir:boolean;
  public cout$: BehaviorSubject<number>= new BehaviorSubject(this.cout);
  private adresse:string;

  constructor(private http: HttpClient,private log:LoginService, private router:Router) {  
    this.ListePlatCommande=[];
    this.ListeFilmCommande=[];
    this.entrainDeSaisir=false;

    this.cout$.subscribe(c=>{
      this.cout=c;
    })
    this.cmdEnvoye$.next(false);
    this.cmdEnvoye$.subscribe(cmd=>{
      this.cmdEnvoye=cmd;
    })
  }

  setAdresse(adr:string){
    this.adresse = adr;
  }

  getAdresse(){
    return this.adresse;
  }

  ajoutFilmPanier(film:MovieResponse){
    this.ListeFilmCommande.push(film);
  }

  enleveFilmPanier(index_remove:number){
    this.ListeFilmCommande.splice(index_remove,1)
  }

  getCout():number{
    return this.cout;
  }
  getPlatPanier():PlatCommande[]{
    return this.ListePlatCommande;
  }
  getMoviePanier():MovieResponse[]{
    return this.ListeFilmCommande;
  }

  getPanier()
  {
    return this.ListePlatCommande;
  }

  ajoutePlatPanier(p:PlatCommande){
    this.triPlatsCommande(p);
    this.emitCoutSubject();
  }
  getPlatCommandeById(id:number):PlatCommande{
    let pc:PlatCommande=null;
    this.ListePlatCommande.forEach((p)=>{
      if(+p.id==id){
        pc=p;
      }
    })
    return pc;
  }


  triPlatsCommande(platc:PlatCommande){
    let peutOnPush:boolean=true;
    let doitOnRemove:boolean=false;
    let index_remove:number;

    if(platc.quantite<=0){
      doitOnRemove=true;
      peutOnPush=false;
    }else{  // Si la quantité vaut 0 inutile de s'embeter à l'ajouter potentiellement à une valeur déja existante
      for(let i=0; i<this.ListePlatCommande.length; i++){
        let pc=this.ListePlatCommande[i];
        if(platc.id===pc.id){ //Le plat est deja dans le panier du client
          this.ListePlatCommande[i].quantite=platc.quantite;
          peutOnPush=false;
        }
      }
    }
    if(peutOnPush){
      this.ListePlatCommande.push(platc);
    }    
    if(doitOnRemove){
      this.ListePlatCommande.splice(index_remove,1);
    }
  }
  getNom(p:PlatCommande):string{
    return p.nom;
  }
  getPrix(p:PlatCommande):number{
    return +p.prix*p.quantite;
  }
  getQuantite(p:PlatCommande):number{
    return +p.quantite;
  }
  setEntrainDeSaisir(b:boolean){
    this.entrainDeSaisir=b;
  }
  getEntrainDeSaisir():boolean{
    return this.entrainDeSaisir;
  }

  adresseSaisi():boolean{
    return this.adresse!=null
  }

  taillePanier(){
    return this.ListePlatCommande.length+this.ListeFilmCommande.length;
  }
  emitCoutSubject(){
    let cout:number=0;
    this.ListePlatCommande.forEach(e=>{
      cout+= +e.prix*e.quantite});
    this.cout$.next(cout);
  }
  emitCMDSubject(boo:boolean){
    this.cmdEnvoye$.next(boo);
  }

  valideAfficheCout():boolean{
    return this.ListePlatCommande.length>0;
  }
  ajoutQuantite(i:number){ 
    this.ListePlatCommande[i].quantite+=1;
    this.emitCoutSubject();
  }

  enleveQuantite(i:number){
    this.ListePlatCommande[i].quantite-=1;
    if(this.ListePlatCommande[i].quantite<=0){
      this.ListePlatCommande.splice(i,1);
    }
    this.emitCoutSubject();
  }
  
  getPlatCommandeQuantite(i:number):number{
    let pc:PlatCommande=this.getPlatCommandeById(i);
    let qte:number=0;
    if(pc){
      qte=pc.quantite;
    }
    return qte;
  }

  //Renvoie la liste des id des films présent dans le panier
  listeIdFilms():string{
    let idTab:string='';
    this.ListeFilmCommande.forEach((film:MovieResponse)=>{
      idTab=idTab+(film.id.toString())+' - '
    })
    return idTab;
  }
  //Renvoie la liste des id des plats présent dans le panier
  listeIdPlats():string{
    let idTab:string='';
    this.ListePlatCommande.forEach((plat:PlatCommande)=>{
      idTab=idTab+(plat.id.toString())+' - '
    })
    return idTab;
  }

  resetPanier(){
    this.ListePlatCommande=[];
    this.ListeFilmCommande=[];
  }

  sendPanierToServeur(){
    let IdClient:string = this.log.getIdClient();
    let IdsFilms:string=this.listeIdFilms();
    let IdsPlats:string=this.listeIdPlats();
    let Prix:string=this.cout.toString();
    let Adresse:string=this.adresse;

    // console.log( "Je t'envoie",  {idUsers:IdClient,
    //   idFilms:IdsFilms,
    //   idPlats:IdsPlats,
    //   prixCommande:Prix,
    //   adresseLivraison: Adresse })

    const repServeur= this.POST( '/api/commande', {
      idUsers:IdClient,
      idFilms:IdsFilms,
      idPlats:IdsPlats,
      prixCommande:Prix,
      adresseLivraison: Adresse 
      });
    console.log("Le serveur repond",repServeur)
    this.emitCMDSubject(true);
    setTimeout(_=>{
      this.resetPanier();
      this.emitCMDSubject(false);
      this.router.navigate(['/acceuil'])},5000);
  }

  commandeEnvoye():boolean{
    return this.cmdEnvoye;
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