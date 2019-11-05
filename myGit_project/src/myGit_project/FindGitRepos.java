package myGit_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.RepositoryNotFoundException;

public class FindGitRepos {

	private List<String> listeRepos;
	private String listeReposGit = "liste_repos_git.txt";

	public void listFolders(String directoryName) throws IOException {
		try (Stream<Path> walk = Files.walk(Paths.get(directoryName))) {
			listeRepos = walk.map(x -> x.
					toString()).
					filter(f -> f.
							endsWith(".git")).
					collect(Collectors.toList());
			//affichage + ecriture dans le fichier
			this.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setListeReposGit(String fileName) {
		this.listeReposGit = fileName;
	}
	
	public String getListeReposGit() {
		return this.listeReposGit;
	}

	public List<String> getListeRepos() {
		return this.listeRepos;
	}

	public String toString() {
		String s = "";
		String absolutePath;
		for (String elt : this.getListeRepos()) {
			File file = new File(elt);
			absolutePath = file.getParentFile().getAbsolutePath();
			s = s + absolutePath + "\n";
			try {
				this.writeToFile(absolutePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return s;
	}


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
}

