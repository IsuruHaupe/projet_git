package projet_java_git;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoriesFinder implements IRepositoriesFinderInterface {
	public List<String> findRepositoriesInDirectory(String directoryPath) throws java.lang.Exception {
		List<String >tmp = null, listeRepos = new ArrayList<String>();
		try (Stream<Path> walk = Files.walk(Paths.get(directoryPath))) {
			tmp = walk.map(x -> x.
					toString()).
					filter(f -> f.
							endsWith(".git")).
					collect(Collectors.toList());
			for(String elt : tmp) {
				listeRepos.add(elt.replace(".git", ""));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listeRepos;
	}
	public List<String> findRepositoriesInFile​(String filePath) throws java.lang.Exception {
		List<String> liste = new ArrayList<String>();
		String line = null;
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while ((line = bufferedReader.readLine()) != null) {
			liste.add(line);
		}
		bufferedReader.close();
		return liste;
	}
}
