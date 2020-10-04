import { Component, OnInit, Input } from '@angular/core';
import { MovieResponse } from '../tmdb-data/Movie';

@Component({
  selector: 'app-affichage-liste-films',
  templateUrl: './affichage-liste-films.component.html',
  styleUrls: ['./affichage-liste-films.component.scss']
})
export class AffichageListeFilmsComponent implements OnInit {
  @Input() liste_films:MovieResponse[];

  constructor() { }

  ngOnInit(): void {
  }

}
