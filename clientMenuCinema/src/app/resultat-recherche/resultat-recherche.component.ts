import { Component, OnInit } from '@angular/core';
import { FilmService } from '../services/film.service';
import { ActivatedRoute } from '@angular/router';
import { Subscribable, Subscription } from 'rxjs';
import { MovieResponse } from '../tmdb-data/Movie';

@Component({
  selector: 'app-resultat-recherche',
  templateUrl: './resultat-recherche.component.html',
  styleUrls: ['./resultat-recherche.component.scss']
})
export class ResultatRechercheComponent implements OnInit {
  
  search: string = ""; // Chaîne de caractère contenant l'élément qu'il faut rechercher
  filmsSEARCH:MovieResponse[]; // liste de films;
  filmsSEARCH_Subscription:Subscription ;



  constructor(private filmService:FilmService,
              private router: ActivatedRoute) 
  {
    this.filmService.searchFilmsFromTMDB(this.filmService.getRecherche());
    
  }

  ngOnInit(): void {
    this.filmsSEARCH_Subscription = this.filmService.filmSEARCH_Subject.subscribe(
      (reponse: any) => {
        this.filmsSEARCH = reponse;

      }

    );

  
    this.search = this.filmService.getRecherche();

  }

  getNb_Res(){
    return this.filmService.getTotal_res();
  }

}
