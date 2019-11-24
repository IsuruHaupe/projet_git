package control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import projet_java_git.Repositories;
import projet_java_git.RepositoriesFinder;


public class ReposController implements IReposControllerInterface {
	private List<String> listeRepos = new ArrayList<String>();
	
	@Override
	public void addReposFromFile(String filePath) {
		RepositoriesFinder finder = new RepositoriesFinder();
		try {
			this.listeRepos = finder.findRepositoriesInFile​(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addReposFromDirectory(String directoryPath) {
		RepositoriesFinder finder = new RepositoriesFinder();
		try {
			this.listeRepos = finder.findRepositoriesInDirectory(directoryPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getRepos() {
		return this.listeRepos;
	}

	@Override
	public Map<String, Boolean> pushAll() throws Exception {
		Repositories reposPusher = new Repositories();
		return reposPusher.push(this.getRepos().toArray(new String[this.getRepos().size()]));
	}

	@Override
	public Map<String, Boolean> pullAll() throws Exception {
		Repositories reposPusher = new Repositories();
		return reposPusher.pull(this.getRepos().toArray(new String[this.getRepos().size()]));
		
	}

	@Override
	public Map<String, String> getAllStates() throws Exception {
		Repositories reposPusher = new Repositories();
		return reposPusher.getState(this.getRepos().toArray(new String[this.getRepos().size()]));
	}

	@Override
	public boolean saveReposToFile(String targetedPath) {
		boolean sucess = true;
		File gitReposList = new File(targetedPath);
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(gitReposList));
			for(String elt : this.getRepos()) {
				writer.write(elt);
			}
			writer.close();
		} catch (IOException e) {
			sucess = false; 
			return sucess;
		}
		return sucess;
	}
}
