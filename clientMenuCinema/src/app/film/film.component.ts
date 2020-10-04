import { Component, OnInit, Input } from '@angular/core';
import { FilmService } from '../services/film.service';
import { Router } from '@angular/router';
import { MovieResponse } from '../tmdb-data/Movie';

@Component({
  selector: 'app-film',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {

  @Input() film:MovieResponse;
  

  constructor(private filmService:FilmService,
              private router: Router)  
  {
     
  }


  ngOnInit(): void {
  }

  afficher_film(){
    this.router.navigate(["film/voir/"+this.film.title]);
    this.filmService.setFilmVOIR(this.film);
  }

}
