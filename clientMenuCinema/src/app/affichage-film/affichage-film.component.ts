import { Component, OnInit } from '@angular/core';
import { FilmService } from '../services/film.service';
import { ActivatedRoute } from '@angular/router';
import { MovieResponse } from '../tmdb-data/Movie';
import { PanierServiceService } from '../services/panier-service.service';

@Component({
  selector: 'app-affichage-film',
  templateUrl: './affichage-film.component.html',
  styleUrls: ['./affichage-film.component.scss']
})
export class AffichageFilmComponent implements OnInit {

  film:MovieResponse;
  titre:string;
  retour:string;
  

  constructor(private filmService : FilmService,
              private route : ActivatedRoute,
              private panier : PanierServiceService) 
  { 

    this.film = this.filmService.getFilmVOIR();
    this.retour = this.filmService.getRetour();
  }

  ngOnInit(): void {
    this.titre = this.route.snapshot.params['leFILM'];
  }

  ajouterFilm(){
    this.panier.ajoutFilmPanier(this.film);

  }

}
