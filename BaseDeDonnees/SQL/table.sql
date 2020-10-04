-- clients 

-- Un client est defini par son numero de client, son nom, son prenom, son adresse, son email, une photo et eventuellement les points de fidelites qu'il a pu acquerir.
CREATE TABLE MC_CLIENT
(
    id_client VARCHAR(50),
    nom_client VARCHAR(50),
    prenom_client VARCHAR(50),
    adresse_client VARCHAR(50),
    email_client VARCHAR(50),
    photo_client VARCHAR(100),
    fidelite_client INT,
    CONSTRAINT pk_mc_client PRIMARY KEY (id_client)
);

-- achats 
-- La base de donnees relationnelle ne stocke pas de maniere redondante les informations si elles sont disponibles ailleurs. 
-- Les films et les plats seront references par leurs identifiants afin de pouvoir les retrouver respectivement dans TheMovieDatabase et la base XML.

CREATE TABLE MC_FILM
(
    id_film INT,
    CONSTRAINT pk_mc_film PRIMARY KEY (id_film),
    CONSTRAINT check_mc_film CHECK (id_film >= 0)
);

CREATE TABLE MC_PLAT
(
    id_plat INT,
    CONSTRAINT pk_mc_plat PRIMARY KEY (id_plat),
    CONSTRAINT check_mc_plat CHECK (id_plat >= 0)
);

-- Une commande est definie par son numero, le client auquel elle appartient, la date et l'heure de la commande, l'adresse de livraison (qui peut etre differente de celle du client associe), et son prix.
CREATE TABLE MC_COMMANDE
(
    id_commande INT,
    id_client VARCHAR(50),
    date_heure_commande DATE,
    adresse_livraison VARCHAR(50),
    prix_commande DECIMAL(10,2),
    CONSTRAINT pk_mc_commande PRIMARY KEY (id_commande),
    CONSTRAINT fk_mc_commande_client FOREIGN KEY (id_client) REFERENCES MC_CLIENT(id_client) ON DELETE CASCADE,
    CONSTRAINT check_mc_commande CHECK (id_commande > 0)
);

-- Une commande est bien sur composee d'un ensemble d'achat. Chaque achat/item/ligne d'une commande peut etre soit un film, soit un plat.
CREATE TABLE MC_ACHAT
(
    id_achat INT,
    id_film INT,
    id_plat INT,
    id_commande INT,
    CONSTRAINT pk_mc_achat PRIMARY KEY (id_achat),
    CONSTRAINT fk_mc_achat_film FOREIGN KEY (id_film) REFERENCES MC_FILM(id_film) ON DELETE CASCADE,
    CONSTRAINT fk_mc_achat_plat FOREIGN KEY (id_plat) REFERENCES MC_PLAT(id_plat) ON DELETE CASCADE,
    CONSTRAINT fk_mc_achat_commande FOREIGN KEY (id_commande) REFERENCES MC_COMMANDE(id_commande) ON DELETE CASCADE,
    CONSTRAINT check_mc_achat CHECK (id_achat > 0)
);

