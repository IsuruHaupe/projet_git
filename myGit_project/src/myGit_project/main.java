package myGit_project;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class main {

	public static void main(String[] args) throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		System.out.println("Début du programme");
		System.setProperty("user.dir", "/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees");
		MyGitClass myGit = new MyGitClass();
		String commande = "";
		boolean verbose = false;
		String cheminVersRepertoire = "";
		String commitMessage = "";
		if(args.length == 4) {
			commande = args[0];
			verbose = true;
			cheminVersRepertoire = args[2];
			commitMessage = args[3];
		}else if(args.length == 3) {
			commande = args[0];
			verbose = true;
			cheminVersRepertoire = args[2];
		} else if (args.length == 2) {
			commande = args[0];
			verbose = true;
		} else if (args.length == 1) {
			commande = args[0];
		} else if (args.length == 0) {
			System.out.println("Pas d'argument en entrée.");
			System.out.println("Fin du programme.");
			System.exit(0);
		}
		
		switch (commande) {
		case "trouverRepos":
			myGit.getRepositories(verbose);
			break;
		case "trouverReposNonAJour":
			myGit.getOutdatedRepositories(verbose);
			break;	
		case "trouverDiffRepos":
			if(cheminVersRepertoire == "") {
				System.out.println("Veuillez spécifier un chemin vers le répertoire à analyser.");
				System.out.println("Fin du programme");
				System.exit(0);
			}
			System.out.println(myGit.getDiffs(myGit.openRepos(cheminVersRepertoire), verbose));
			break;
		case "trouverDiffReposAll":
			System.out.println(myGit.getAllDiffs(verbose).toString());
			break;
		case "mettreAJourLocal": 
			System.out.println(myGit.update(myGit.openRepos(cheminVersRepertoire), verbose));
			break;
		case "mettreAJourLocalAll":
			System.out.println(myGit.updateAll(verbose));
			break;
		case "mettreAJourRemote": 
			myGit.pushChange(myGit.openRepos(cheminVersRepertoire), commitMessage, verbose);
			break;
		case "mettreAJourRemoteAll":
			myGit.pushChangeAll(commitMessage, verbose);
			break;
		case "lireFichier": 
			System.out.println("Lecture du fichier.");
			for(String elt : myGit.readFromFile()) {
				System.out.println(elt);
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + commande);
		}
		System.out.println("Fin du programme");
	}
}
