-- La procedure suuivante permet de supprimer un client avec comme identifiant id_client 
CREATE OR REPLACE PROCEDURE delete_client(id_C IN MC_CLIENT.id_client%TYPE)
IS
BEGIN 
	DELETE FROM MC_CLIENT WHERE id_client = id_C ;
	COMMIT;
END;
/

-- La procedure suuivante permet de supprimer un achat avec comme identifiant id_achat
CREATE OR REPLACE PROCEDURE delete_achat(id_A IN MC_ACHAT.id_achat%TYPE)
IS
BEGIN 
	DELETE FROM MC_ACHAT WHERE id_achat = id_A ;
	COMMIT;
END;
/

-- La procedure suuivante permet de supprimer une commande avec comme identifiant id_commande
CREATE OR REPLACE PROCEDURE delete_commande(id_COM IN MC_COMMANDE.id_commande%TYPE)
IS
BEGIN 
	DELETE FROM MC_COMMANDE WHERE id_commande = id_COM ;
	COMMIT;
END;
/