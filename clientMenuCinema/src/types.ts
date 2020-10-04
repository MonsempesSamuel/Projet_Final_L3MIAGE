export interface Plat {
    id:string;
    nom:string;
    prix:string;
    imageUrl:string;
    type:string;
    ingredients:string[];
}

export interface PlatCommande {
    id:string;
    nom:string;
    prix:string;
    imageUrl:string;
    type:string;
    ingredients:String[];
    quantite:number;
}

export interface Commande {
    date:string,
    idCommande:string,
    idsPlats:string[],  //Liste d'id de plats
    idsFilms:string[],  //Liste d'id de Films
    prix:string,        //Cout de la commande
    adresse:string      //Adrersse de livraison
}

export interface Users {
    idClient:string,
    nom:string,
    prenom:string,
    photoURL:string,
    mail:string,
    tel:string
}

export interface Utilisateur {
    idClient:string,
    nom:string,
    prenom:string,
    photoURL:string,
    mail:string,
    tel:string,
    nbTotalCommande:string,
    derniereCommande:Commande
}