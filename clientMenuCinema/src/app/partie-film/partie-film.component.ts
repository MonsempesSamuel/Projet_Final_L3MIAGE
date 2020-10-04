import { Component, OnInit, OnDestroy } from '@angular/core';
import { FilmService } from '../services/film.service';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';
import { Subscription } from 'rxjs/Rx';
import { MovieResponse } from '../tmdb-data/Movie';

@Component({
  selector: 'app-partie-film',
  templateUrl: './partie-film.component.html',
  styleUrls: ['./partie-film.component.scss']
})
export class PartieFilmComponent implements OnInit {

  films:MovieResponse[]; // liste de films
  filmSubscription:Subscription; //

  constructor(private filmService:FilmService){
    this.filmService.getFilmsPOPFromTMDB();
    this.filmService.setRetour("/film")
    
  }

  ngOnInit(): void {
    this.filmSubscription = this.filmService.filmPOP_Subject.subscribe(
      (reponse: any) => {
        this.films = reponse;

      }
    );



    this.filmService.emitFilmPOPSubject();
  }

  
}
