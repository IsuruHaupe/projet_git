package myGit_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Repository;

public class MyGitClass implements IMyGit{
	private String listeReposGit = "liste_repos_git.txt";
	/**
	 * Méthode permettant de lister tous les repos Git trouvés à partir 
	 * d'un point d'entrée. Ce dernier est le répertoire courant où est 
	 * lancé l'appel. 
	 * Si le paramètre "verbose" est utilisé, tous les repertoires git
	 * sont écrits dans un fichier par défaut appelé "liste_repos_git.txt",
	 * ce dernier est créé dans le répertoire courant. 
	 * 
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * @return une liste contenant des objets Repository.
	 * 
	 */
	public List<Repository> getRepositories(boolean verbose) {
		List<Repository> listeRepos = new ArrayList<Repository>();
		String workingDirectory = System.getProperty("user.dir");
		FindGitRepos search = new FindGitRepos();
		try {
			if(verbose) {
				System.out.println("Recherche de repos git.");
			}
			search.listFolders(workingDirectory);
			if(verbose) {
				System.out.println("Dépôt(s) trouvé(s).");
				System.out.println(search.toString());
			}
			List<String> listeReposString = search.getListeRepos();
			if(verbose) {
				System.out.println("Test d'ouverture des repos.");
			}
			for(int i = 0; i < listeReposString.size(); i++) {
				listeRepos.add(this.openRepos(listeReposString.get(i)));
			}
			if(verbose) {
				System.out.println("Fin de la recherche.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listeRepos;
	}
	
	/**
	 * Méthode permettant de lire tous les repos git trouvés et 
	 * écrit dans un fichier. Ce fichier est créé par la méthode
	 * "getRepositories" si le mode verbeux est activé. 
	 * 
	 */
	public void printFoundedReposFromFile() {
		try {
			System.out.println("Lecture du fichier.");
			for(String elt : this.readFromFile()) {
				System.out.println(elt);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'ajouter un nouveau repertoire git à suivre
	 * dans le fichier.
	 * 
	 * @param reposGitPath le chemin absolu vers le repertoire git
	 */
	public void addNewReposToFile(String reposGitPath) {
		try {
			this.writeToFile(reposGitPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant de trouver tous les répertoires git non à jour.
	 * 
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * 
	 * @return retourne une liste d'objet Repository contenant les répertoires git 
	 * non à jour.
	 */
	public List<Repository> getOutdatedRepositories(boolean verbose) {
		List<Repository> listeReposGitNonAJour = new ArrayList<Repository>();
		List<String> listeRepos = null;
		try {
			listeRepos = this.readFromFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CheckStatus reposGit = new CheckStatus();
		if(verbose) {
			System.out.println("Parcours des répertoires git pour trouver ceux non à jour.");
		}
		for(String elt : listeRepos) {
			System.out.println("Dépot" + elt);
			try {
				reposGit.setReposGit(this.openRepos(elt));
				if(verbose) {
					try {
						reposGit.checkStatusVerbose();
					} catch (NoWorkTreeException | GitAPIException e) {
						e.printStackTrace();
					}
				}
				try {
					if(reposGit.checkStatus()) {
						listeReposGitNonAJour.add(reposGit.getReposGit());
					}
				} catch (NoWorkTreeException | GitAPIException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return listeReposGitNonAJour;
	}

	/**
	 * Méthode permettant de récupérer les diff pour un répertoire donné
	 * @param repository : objet Repository faisant référence au répertoire git
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * @return un String contenant les diff pour le répertoire git.
	 */
	public String getDiffs(Repository repository, boolean verbose) {
		String res = "";
		GitDiff gitDiff = new GitDiff();
		if(verbose) {
			System.out.println("Recherche de diff dans le répertoire git.");
		}
		gitDiff.setReposGit(repository);
		try {
			res = gitDiff.gitDiff();
		} catch (RevisionSyntaxException e) {
			e.printStackTrace();
		} catch (AmbiguousObjectException e) {
			e.printStackTrace();
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Méthode permettant de récupérer toutes les diff des répertoires git 
	 * enregistrés dans le fichier. Ce dernier est créé par la méthode 
	 * "getRepositories" en mode verbeux (verbose = true);
	 * 
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * @return une liste de String contenant les diff pour les répertoires.
	 */
	public List<String> getAllDiffs(boolean verbose) {
		List<String> resDiff = new ArrayList<String>();
		try {
			for(String elt : this.readFromFile()) {
				try {
					if(verbose) {
						System.out.println("Repos : " + elt);
					}
					resDiff.add(this.getDiffs(this.openRepos(elt), verbose));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resDiff;
	}

	/**
	 * Méthode effectuant un git pull pour récupérer les mise à jours sur le repos git.
	 *
	 * @param repository : objet Repository faisant référence au répertoire git.
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * @return : un string indiquant le déroulement du git pull.
	 */
	public String update(Repository repository, boolean verbose) {
		String res = "";
		GitPull pullRepos = new GitPull();
		pullRepos.setReposGit(repository);
		if(verbose) {
			System.out.println("Mise à jour des modifications locales depuis le répertoire git en ligne.");
		}
		try {
			res = pullRepos.gitPull();
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (CanceledException e) {
			e.printStackTrace();
		} catch (RefNotFoundException e) {
			e.printStackTrace();
		} catch (RefNotAdvertisedException e) {
			e.printStackTrace();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Méthode effectuant un git pull sur tous les repos enregistrés dans le fichier.
	 *
	 * @param verbose : true ou false pour avoir des retours de la fonction sur le déroulement.
	 * @return : une liste de string indiquant le déroulement du git pull.
	 */
	public List<String> updateAll(boolean verbose) {
		List<String> res = new ArrayList<String>();
		try {
			for(String elt : this.readFromFile()) {
				System.out.println(elt);
				res.add(this.update(this.openRepos(elt), verbose));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Repository openRepos(String reposPath) throws IOException {
		File repos = new File(reposPath);
		Git git = null;
		try {
			git = Git.open(repos);
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}		
		return git.getRepository();
	}

	/**
	 * Méthode permettant d'effectuer un git push sur le repos concerné.
	 * La méthode demande à l'utilisateur son identifiant et son mot de passe.
	 * 
	 * @param commitMessage : message pour le commit.
	 */
	public void pushChange(Repository repository, String commitMessage, boolean verbose) {
		GitPush gitPush = new GitPush();
		gitPush.setReposGit(repository);
		try {
			if(verbose) {
				System.out.println("Lancement d'une mise à jour vers le répertoire en ligne (git push).");
			}
			gitPush.gitPush(commitMessage);
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode permettant d'effectuer un git push sur tous les repos enregistrés dans le fichier.
	 * La méthode demande à l'utilisateur son identifiant et son mot de passe.
	 * 
	 * @param commitMessage : message pour le commit.
	 */
	public void pushChangeAll(String commitMessage, boolean verbose) {
		List<String> res = new ArrayList<String>();
		try {
			for(String elt : this.readFromFile()) {
				this.pushChange(this.openRepos(elt), commitMessage, verbose);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant d'ajouter un répertoire git dans le fichier 
	 * enregistrant tous les dépôts git à suivre.
	 * 
	 * @param fileName : chemin absolu vers le répertoire git.
	 */
	public void writeToFile(String fileName) throws IOException {
		File gitReposList = new File(this.getListeReposGit());
		BufferedWriter writer;
		if (gitReposList.exists()) {
			writer = new BufferedWriter(new FileWriter(gitReposList, true));
			writer.newLine();
			writer.append(fileName);
		} else {
			writer = new BufferedWriter(new FileWriter(gitReposList));
			writer.write(fileName);
		}
		writer.close();
	}
	
	/**
	 * Méthode permettant de lire le fichier contenant tous les fichiers 
	 * git à suivre.
	 * @return une liste de String contenant le chemin absolu vers les répertoires git.
	 */
	public List<String> readFromFile() throws IOException {
		List<String> liste = new ArrayList<String>();
		String fileName = this.getListeReposGit();
		String line = null;

		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			liste.add(line);
		}
		bufferedReader.close();
		return liste;
	}
	
	public String getListeReposGit() {
		return this.listeReposGit;
	}
}
