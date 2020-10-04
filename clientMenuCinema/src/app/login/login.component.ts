import { Component, OnInit } from '@angular/core';
import {auth, User} from 'firebase/app';
import {AngularFireAuth} from '@angular/fire/auth';
import { HttpClient, HttpHeaderResponse,HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  
  ngOnInit(): void {}
  constructor(private log: LoginService ) {}

  connexionGoogle(){
    this.log.loginGoogle();
  }
  isConnected(){
    return this.log.isAuth();
  }
  deconnexion(){
    this.log.signOutUser();
  }
  getUrlPhotoProfil(){
    return this.log.getUrlPhotoProfil();
  }
  getNomUtilisateur(){
    return this.log.getNomUtilisateur();
  }
  getPrenomUtilisateur(){
    return this.log.getPrenomUtilisateur()
  }
  getEmail(){
    return this.log.getEmail();
  }
  getPhoneNumber(){
    return this.log.getPhoneNumber();
  }
  

}
