import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { PanierServiceService } from '../services/panier-service.service';

@Component({
  selector: 'app-adresse-client',
  templateUrl: './adresse-client.component.html',
  styleUrls: ['./adresse-client.component.scss']
})
export class AdresseClientComponent implements OnInit {
  rue:string;
  ville:string;
  tel:string;
  pays:string;
  codeP: string;
  infoManquante:boolean=false;

  constructor(private panier:PanierServiceService) { }

  ngOnInit(): void {
  }

  onSubmit(form:NgForm){
    this.rue = form.value.rue;
    this.ville = form.value.ville;
    this.tel = form.value.tel;
    this.pays = form.value.pays;
    this.codeP = form.value.codeP;
    if(this.rue!=""&&this.codeP!=""&&this.ville!=""){
      this.panier.setAdresse(this.rue + ", " + this.codeP + " " + this.ville);
    }else{
      this.infoManquante=true;
    }
    console.log(this.panier.getAdresse());
  }

  ManqueVille():boolean{
    return this.infoManquante&&this.ville=="";
  }
  ManqueCP():boolean{
    return this.infoManquante&&this.codeP=="";
  }
  ManqueRue():boolean{
    return this.infoManquante&&this.rue=="";
  }




}
