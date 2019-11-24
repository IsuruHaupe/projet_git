package projet_java_git;

import java.util.List;

public interface IRepositoriesFinderInterface {
	/**
	 * An utility that parse a directory 
	 * and all it's child in order to find all the repositories in.
	 * @param directoryPath : the path to the root directory.
	 * @return : a list of all repositories paths without ".git".
	 * @throws java.lang.Exception : throws Exception
	 */
	List<String> findRepositoriesInDirectory(String directoryPath) throws java.lang.Exception;
	/**
	 * An utility that parse a file and return all the directories that are contained in.
	 * @param filePath : the path to the file.
	 * @return : a list of all repositories paths without ".git".
	 * @throws java.lang.Exception : throws Exception
	 */
	List<String> findRepositoriesInFile​(String filePath) throws java.lang.Exception;
}
