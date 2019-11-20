package projet_java_git;

import java.util.List;

public interface IRepositoriesFinderInterface {
	List<String> findRepositoriesInDirectory(String directoryPath) throws java.lang.Exception;
	List<String> findRepositoriesInFile​(String filePath) throws java.lang.Exception;
}
