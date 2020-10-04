import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms"
import { FilmService } from '../services/film.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recherche-film',
  templateUrl: './recherche-film.component.html',
  styleUrls: ['./recherche-film.component.scss']
})
export class RechercheFilmComponent implements OnInit {

  constructor(private filmService:FilmService,
              private router: Router)  
  {

  }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm){
    this.filmService.setRecherche(form.value['search']);
    this.filmService.searchFilmsFromTMDB(this.filmService.getRecherche());
    this.router.navigate(["film/search/"+this.filmService.getRecherche()]);
    this.filmService.setRetour("/film/search/"+this.filmService.getRecherche());
  }

}
