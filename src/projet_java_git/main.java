package projet_java_git;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import control.ReposController;

public class main {

	public static void main(String[] args) {
		System.out.println("Début du programme.");
		Repository repo = new Repository();
		RepositoriesFinder reposFinder = new RepositoriesFinder();
		try {
			//repo.add("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast",
			//		"newfile_zezieu.txt", 
			//		"other_file.txt");
			//repo.commit("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast",
			//		"test");
			//System.out.println(repo.getNotAddedFiles("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast"));
			
			//System.out.println(repo.diffs("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast"));
			//System.out.println(repo.getState("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast"));
			//System.out.println(repo.pull("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast"));
			//System.out.println(repo.push("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast"));
			//System.out.println(Arrays.asList(repo.status("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/learn_math_fast")));
			/*for(String elt : reposFinder.findRepositoriesInDirectory("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/")) {
				System.out.println(elt);
			};*/
			
			/*for(String elt : reposFinder.findRepositoriesInFile​("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/filePath.txt")) {
				System.out.println(elt);
			};*/
			///######TEST CONTROLLEUR#########//
			ReposController control = new ReposController();
			/*
			control.addReposFromFile("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/liste_repos_git.txt");
			for(String elt : control.getRepos()) {
				System.out.println("repos : " + elt);
			}
			*/
			
			control.addReposFromDirectory("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees");
			/*for(String elt : control.getRepos()) {
				System.out.println("directory : " + elt);
			}*/
			
			
			//Map<String, Boolean> res = control.pushAll();
			//System.out.println(res.toString());
			
			//Map<String, Boolean> res = control.pullAll();
			//System.out.println(res.toString());
			
			//Map<String, String> res = control.getAllStates();
			//System.out.println(res.toString());
			
			control.saveReposToFile("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/donnees/test.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Fin du programme.");
	}

}
