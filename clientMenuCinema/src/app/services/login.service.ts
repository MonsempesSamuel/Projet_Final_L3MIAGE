import { User, auth } from 'firebase';
import { AngularFireAuth } from '@angular/fire/auth';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Users, Utilisateur, Commande } from 'src/types';
import { Router } from '@angular/router';

@Injectable()
export class LoginService {
    private validePanier:boolean=false;
    user:User;
    infoUser:Utilisateur;

    constructor( private auth: AngularFireAuth, private http: HttpClient,private router:Router ) {
        this.resetInfoUser();
        auth.user.subscribe( async (u: User) => {
          this.user=u;
        // On contacte le serveur métier pour l'informer si un nouvel utilisateur existe :
          if (u !== null) {

            const reponseServeur= await this.POST(' /api/authentification', {
                idClient:u.uid,
                nom:u.displayName.split(' ')[1],
                prenom:u.displayName.split(' ')[0],
                photoURL:u.photoURL,
                mail:u.email,
                tel:u.phoneNumber
            })
            if(reponseServeur.status==200){
                //console.log('Le serveur répond', reponseServeur.body);
                this.setInfoUser(reponseServeur.body)
                let utilisateur:Users = {
                    idClient:u.uid,
                    nom:u.displayName.split(' ')[1],
                    prenom:u.displayName.split(' ')[0],
                    photoURL:u.photoURL,
                    mail:u.email,
                    tel:u.phoneNumber
                }
                //console.log(utilisateur)
                }
            }
            
        });
    }

    getCommande(text){
        
        // this.infoUser.nbTotalCommande = xmlClient.getElementsByTagName("NbTotalCommande").item(0).innerHTML;
        // let idsFilms=xmlClient.getElementsByTagName("idfilm")
        // let listIdFilm:string[]=[];
        // for(let j = 0; j < idsFilms.length; j++){
        //     listIdFilm.push(idsFilms.item(j).innerHTML);
        // }
        // let idsRepas=xmlClient.getElementsByTagName("idrepas")
        // let listIdRepas:string[]=[];
        // for(let j = 0; j < idsRepas.length; j++){
        //     listIdRepas.push(idsRepas.item(j).innerHTML);
        // }
        // let Prixlc = xmlClient.getElementsByTagName("prix").item(0).innerHTML;
        // let Adresse = xmlClient.getElementsByTagName("DerniereAdresse").item(0).innerHTML;
        // let Date = xmlClient.getElementsByTagName("date").item(0).innerHTML;
        // let idCom = xmlClient.getElementsByTagName("idCommande").item(0).innerHTML;

        // let lastCommande:Commande={
        //     date:Date,
        //     idCommande:idCom,
        //     idsPlats:listIdRepas,  //Liste d'id de plats
        //     idsFilms:listIdFilm,  //Liste d'id de Films
        //     prix:Prixlc,        //Cout de la commande
        //     adresse:Adresse      //Adrersse de livraison
        // };
        // this.infoUser.derniereCommande=lastCommande
    }

    setInfoUser(text){
        // let text=
        // "<?xml version= "1.0" encoding= "UTF-8"?>"+
        // "<Client>"+
        // "<idClient>1</idClient>"+
        // "<Nom>Dumas</Nom>"+
        // "<Prénom>Clément</Prénom>"+
        // "<Photo>Photo</Photo>"+
        // "<Mail>clmeent.dumas38@gmail.com</Mail>"+
        // "<Tel>null</Tel>"+
        // "<NbTotalCommande>1</NbTotalCommande>"+
        // "<DerniereCommande>"+
        //    "<idCommande>1</idCommande>"+
        //    "<date>a</date>"+
        //    "<idplats>"+
        //      "<idplat>a</idplat>"+
        //    "</idplats>"+
        //    "<idfilms>"+
        //      "<idfilm>a</idfilm>"+
        //    "</idfilms>"+        
        //    "<prix>21</prix>"+
        //    "<DerniereAdresse>16ruejacque</DerniereAdresse>"+
        //   "</DerniereCommande>"+
        // "</Client>"
        let parser = new DOMParser();
        let xmlClient = parser.parseFromString(text, "application/xml");
        this.infoUser.idClient = xmlClient.getElementsByTagName("idClient").item(0).innerHTML;
        this.infoUser.prenom = xmlClient.getElementsByTagName("Prénom").item(0).innerHTML;
        this.infoUser.nom = xmlClient.getElementsByTagName("Nom").item(0).innerHTML;
        this.infoUser.photoURL= xmlClient.getElementsByTagName("Photo").item(0).innerHTML;
        this.infoUser.mail = xmlClient.getElementsByTagName("Mail").item(0).innerHTML;
        this.infoUser.tel = xmlClient.getElementsByTagName("Tel").item(0).innerHTML;
        if (this.infoUser.tel===null){
            this.infoUser.tel=' '
        }
    }   
    
    isAuth(){
        return +this.infoUser.idClient!=-1;
    }
    
    getIdClient():string{
        return this.infoUser.idClient;
    }
    getNomUtilisateur():string{
        return this.infoUser.nom;
    }
    getPrenomUtilisateur():string{
        return this.infoUser.prenom;
    }
    getUrlPhotoProfil():string{
        return this.infoUser.photoURL;
    }
    getEmail(){
        return this.infoUser.mail;
    }
    getPhoneNumber(){
        return this.infoUser.tel;
    }
    

    setTrueValideCommande(){
        this.validePanier=true;
    }
    setFalseValideCommande(){
        this.validePanier=false;
    }

    loginGoogle() {
        this.auth.signInWithPopup(new auth.GoogleAuthProvider());
    }
    signOutUser() {
        this.auth.signOut();
        this.resetInfoUser();
    }
    resetInfoUser(){
        this.infoUser={
            idClient:'-1',
            nom:'',
            prenom:'',
            photoURL:'',
            mail:'',
            tel:'',
            nbTotalCommande:'',
            derniereCommande:{
                date:'',
                idCommande:'',
                idsPlats:[''],  //Liste d'id de plats
                idsFilms:[''],  //Liste d'id de Films
                prix:'',        //Cout de la commande
                adresse:''      //Adrersse de livraison
            }
        }
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