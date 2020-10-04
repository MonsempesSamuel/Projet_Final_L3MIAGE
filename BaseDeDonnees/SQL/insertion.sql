-- on insere les donnees dans la table client 
INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES (1, 'Zidane', 'Zinedine', '10, avenue de Madrid', 'zz10@yahoo.com', 'zz.png', 10);
INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES (2, 'Benzema', 'Karim', '9, avenue de Madrid', 'kb9@yahoo.com', 'kb9.png', 9);
INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES (3, 'Ronaldo', 'Cristiano', '7, rue de la veille Dame', 'cr7@yahoo.com', 'cr7.png', 7);
INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES (4, 'Mahrez', 'Riyad', '26, rue de city', 'rm26@yahoo.com', 'rm26.png', 26);
INSERT INTO MC_CLIENT (id_client, nom_client, prenom_client, adresse_client, email_client, photo_client, fidelite_client) VALUES (5, 'Ramos', 'Sergio', '4, avenue de Madrid', 'rs4@yahoo.com', 'rs4.png', 4);

-- On insere les donnees dans la table film 
INSERT INTO MC_FILM (id_film) VALUES (1);
INSERT INTO MC_FILM (id_film) VALUES (2);
INSERT INTO MC_FILM (id_film) VALUES (3);
INSERT INTO MC_FILM (id_film) VALUES (4);
INSERT INTO MC_FILM (id_film) VALUES (5);

-- On insere les donnees dans la table plat 
INSERT INTO MC_PLAT(id_plat) VALUES (1);
INSERT INTO MC_PLAT(id_plat) VALUES (2);
INSERT INTO MC_PLAT(id_plat) VALUES (3);
INSERT INTO MC_PLAT(id_plat) VALUES (4);
INSERT INTO MC_PLAT(id_plat) VALUES (5);

-- On insere les donnees dans la table commande TO_DATE('2020/04/21 20:00:00', 'yyyy/mm/dd hh24:mi:ss')
INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (1, 1, TO_DATE('2020/04/06 12:30:00', 'yyyy/mm/dd hh24:mi:ss'), '10, avenue de Madrid', 5.91);
INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (2, 2, TO_DATE('2020/04/07 13:00:00', 'yyyy/mm/dd hh24:mi:ss'), '9, avenue de Madrid', 15.90);
INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (3, 3, TO_DATE('2020/04/08 19:00:00', 'yyyy/mm/dd hh24:mi:ss'), '7, rue de la veille Dame', 8.90);
INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (4, 4, TO_DATE('2020/04/09 19:30:00', 'yyyy/mm/dd hh24:mi:ss'), '26, rue de city', 18.90);
INSERT INTO MC_COMMANDE (id_commande, id_client, date_heure_commande, adresse_livraison, prix_commande) VALUES (5, 5, TO_DATE('2020/04/21 20:00:00', 'yyyy/mm/dd hh24:mi:ss'), '4, avenue de Madrid', 4.50);


-- On insere les donnees dans la table achat
INSERT INTO MC_ACHAT (id_achat, id_film, id_plat, id_commande) VALUES (1, 1, 1, 1);
INSERT INTO MC_ACHAT (id_achat, id_film, id_plat, id_commande) VALUES (2, 2, 2, 2);
INSERT INTO MC_ACHAT (id_achat, id_film, id_plat, id_commande) VALUES (3, 3, 3, 3);
INSERT INTO MC_ACHAT (id_achat, id_film, id_plat, id_commande) VALUES (4, 4, 4, 4);
INSERT INTO MC_ACHAT (id_achat, id_film, id_plat, id_commande) VALUES (5, 5, 5, 5);
