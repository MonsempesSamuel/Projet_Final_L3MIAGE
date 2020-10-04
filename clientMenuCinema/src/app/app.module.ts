import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TmdbService} from './tmdb.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { AngularFireModule } from '@angular/fire';
import { environment } from '../environments/environment';
import { AngularFireAnalyticsModule } from '@angular/fire/analytics';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import { AngularFireAuthModule } from  '@angular/fire/auth';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from '@angular/router';
import { AffichagePlatComponent } from './affichage-plat/affichage-plat.component';
import { PlatComponent } from './plat/plat.component';
import { CommandeComponent } from './commande/commande.component';
import { PanierClientComponent } from './panier-client/panier-client.component';
import { PartiePlatComponent } from './partie-plat/partie-plat.component';
import { PartieFilmComponent } from './partie-film/partie-film.component';
import { MenuCinemaViewComponent } from './menu-cinema-view/menu-cinema-view.component';
import { LoginService } from './services/login.service';
import { PanierServiceService } from './services/panier-service.service';
import { NotFoundComponent } from './not-found/not-found.component';
import { LoginGuard } from './services/login-guard.service';
import { MenuCinemaView } from './services/menuCinemaView.service';
import { PlatService } from './services/platService.service';
import { FilmService } from './services/film.service';
import { FilmComponent } from './film/film.component';
import { RechercheFilmComponent } from './recherche-film/recherche-film.component';
import { ResultatRechercheComponent } from './resultat-recherche/resultat-recherche.component';
import { ValidePanierComponent } from './valide-panier/valide-panier.component';
import { AffichageFilmComponent } from './affichage-film/affichage-film.component';
import { AffichageListeFilmsComponent } from './affichage-liste-films/affichage-liste-films.component';
import { AdresseClientComponent } from './adresse-client/adresse-client.component';

 const appRoutes: Routes = [
  {path: 'acceuil', component:MenuCinemaViewComponent},
  {path: '', redirectTo:'/acceuil',pathMatch:'full'},
  {path:'film',component:PartieFilmComponent},
  {path:'menu',component:PartiePlatComponent},
  {path:'login', component:LoginComponent },
  {path:'validation_commande',canActivate: [LoginGuard], component:ValidePanierComponent},
  {path:'not-found',component:NotFoundComponent},
  // Mohamed: cette ligne a été commenté pour pouvoir utilisé les root pour transférer des données à une autre page 
  //{path:'**', redirectTo:'not-found'},
  {path: 'film/search/:leFILM', component: ResultatRechercheComponent },
  {path: 'film/voir/:leFILM', component: AffichageFilmComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AffichagePlatComponent,
    CommandeComponent,
    PlatComponent,
    PanierClientComponent,
    PartiePlatComponent,
    PartieFilmComponent,
    MenuCinemaViewComponent,
    NotFoundComponent,
    FilmComponent,
    RechercheFilmComponent,
    ResultatRechercheComponent,
    ValidePanierComponent, 
    AffichageFilmComponent,
    AffichageListeFilmsComponent,
    AdresseClientComponent 
  ],
  imports: [
    BrowserModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFireAuthModule,
    AngularFireAnalyticsModule,
    AngularFirestoreModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    HttpClientModule
  ],
  providers: [TmdbService, HttpClient,LoginService,PanierServiceService,LoginGuard,MenuCinemaView,PlatService,FilmService],
  bootstrap: [AppComponent]
})
export class AppModule { }
