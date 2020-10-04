import { Subject } from 'rxjs/Subject';
import { HttpClientModule, HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { MovieResponse } from '../tmdb-data/Movie';

@Injectable()
export class FilmService {

    filmPOP_Subject = new Subject<any>();
    filmSEARCH_Subject = new Subject<any>();

    private filmsPOP:MovieResponse[]; // liste contenant des listes de 4 films
    private filmsSEARCH:MovieResponse[]; // liste de films
    private total_res:number;
    private filmVOIR:MovieResponse; // film à afficher dans affichage film

    private recherche:string;   // 

    private url_retour:string = "/film";



    constructor(private httpClient: HttpClient){

    }

    setRetour(url_retour:string){
        this.url_retour = url_retour;
    }

    getRetour(){
        return this.url_retour;
    }

    setRecherche(recherche:string){
        this.recherche = recherche;
    }

    getRecherche(){
        return this.recherche;
    }

    setFilmVOIR(film:MovieResponse){
        this.filmVOIR = film;
    }

    getFilmVOIR(){
        return this.filmVOIR;
    }

    getTotal_res(){
        return this.total_res;
    }

    emitFilmPOPSubject() {
       this.filmPOP_Subject.next(this.filmsPOP) ;
    }

    emitFilmSEARCH_Subject(){
        this.filmSEARCH_Subject.next(this.filmsSEARCH);
    }

    getFilmsPOPFromTMDB() {
        let url_populaire = 'https://api.themoviedb.org/3/movie/popular?api_key=956137382f9d19410ff692e7a9865512&language=fr-FR&page=1';

        this.httpClient
            .get(url_populaire)
            .subscribe(
                (reponse:any) => {
                    this.filmsPOP = reponse.results;
                    
                    this.emitFilmPOPSubject();
        
                    
                },
                (error) => {
                    console.log('Ca a pas marcher ohh !' + error);
                }
                
            );

        
    }

    searchFilmsFromTMDB(search : string){
        this.total_res = 0;

        let url_search = "https://api.themoviedb.org/3/search/movie?api_key=956137382f9d19410ff692e7a9865512&language=fr-FR&query="+search+"&page=1&include_adult=false";


        // Envoi de la requête

        this.httpClient
            .get(url_search)
            .subscribe(
                (reponse:any) => {

                    this.filmsSEARCH = reponse.results ;
                    this.emitFilmSEARCH_Subject();
                    this.total_res = reponse.results.length;
                },
                (error) => {
                    console.log('Ca a pas marcher ohh !' + error);
                }
                
            );


    }


}