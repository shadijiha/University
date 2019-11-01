/*********************************************************
Fichier     : formatif-fonctions.h
Auteur      : Domingo Palao Munoz
Date        : 30 octobre 2019
Description : fichier avec les prototypes de fonctions
**********************************************************/
/* NE CHANGEZ PAS CE FICHIER */


#ifndef FORMATIF_FONCTIONS_H
#define FORMATIF_FONCTIONS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Le nom des fichiers
//Assurez-vous de placer les fichiers de donnees dans les bons repertoires
#define  FICHIER_GARCONS "prenoms-garcons.txt"
#define  FICHIER_FILLES "prenoms-filles.txt"
#define  FICHIER_SORTIE_GARCONS "rapport-garcons.txt"
#define  FICHIER_SORTIE_FILLES "rapport-filles.txt"

//Le nombre d'elements du rapport
#define NOMBRE_ELEMENTS_RAPPORT 5

//Pour l'affichage du sexe
#define GARCONS "garcons"
#define  FILLES "filles"

// Le nombre d'entrees dans le fichier
#define MAX_NOMS 1000

#define TAILLE_NOMS 26
#define TAILLE_POPULARITE 7

// Structure
struct PrenomPopularite
{
	char prenom[TAILLE_NOMS];
	char popularite[TAILLE_POPULARITE];
};

// Definition des fonctions

// Afficher le menu utilisateur
// parameters: aucun
// return : aucun
void afficherMenu();

// Lire un fichier selon le format indique
// parameters: 
//         fichier: le nom du fichier a traiter
//         tableauPrenomPopularite : le tableau avec les noms 
// return : 
//         1 : si tout se passe correctement
//         0 : s'il y a une erreur
int lireFichier(char fichier[],
	struct PrenomPopularite tableauPrenomPopularite[]);

// chercher votreNom dans les deux tableaux et affiche les messages
// parameters: 
//         tableauPrenomPopulariteGarcons : le tableau avec les noms des garcons
//         tableauPrenomPopulariteFilles : le tableau avec les noms des filles
//         votreNom : le nom a chercher
// return : 
//         1 : si tout se passe correctement
//         0 : s'il y a une erreur
int chercherNom(struct PrenomPopularite tableauPrenomPopulariteGarcons[],
	struct PrenomPopularite tableauPrenomPopulariteFilles[], char votreNom[]);

// Cherche votreNom dans un tableau et retourne la position dans un paramètre
// parameters: 
//         tableauPrenomPopularite : un tableau contenant les noms et la popularite
//         votreNom : le nom a chercher
//         position : la position dans le tableau ou se trouve le nom
// return : 
//         1 : si tout se passe correctement
//         0 : s'il y a une erreur
int chercherNomTableau(struct PrenomPopularite tableauPrenomPopularite[],
	char votreNom[], int* position);

// Afficher le mesage lors que le nom a ete trouve
// parameters :
//         nom : le nom trouve
//         position : la position du nom
//         SEXE : le sexe du nom
//         popularite : la popularite du nom
// return :
//         aucun
void afficheMessageNomTrouve(char nom[], int position, char SEXE[],
	char popularite[]);

//Afficher le message lors que le nom n'a pas ete trouve
// parameters :
//         nom : le nom non trouve
//         SEXE : le sexe du nom
void afficheMessageNomNonTrouve(char nom[], char SEXE[]);

//Creer le rapport de 5 noms plus populaires
// parameters : 
//         nomFIchier : le nom du fichier de sortie
//         sexe : le sexe du tableau
//         nombreElementsRapport : combien d'elements a afficher dans le rapport
//         tableauPrenomPopularite : le tableau de donnees
int creerRapport(char nomFichier[], char sexe[], int nombreElementsRapport,
	struct PrenomPopularite tableauPrenomPopularite[]);

#endif
