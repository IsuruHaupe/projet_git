package projet_java_git;

import java.util.List;
import java.util.Map;

public interface IRepositoryInterface {
	void add(String pathToRepository, String... pathsToFiles) throws java.lang.Exception;
	void commit(String pathToRepository, String message);
	String diffs(String pathToRepository);
	List<String> getNotAddedFiles(String pathToRepository);
	String getState(String pathToRepository);
	boolean pull(String pathToRepository);
	boolean pull(String pathToRepository, String username, String password);
	boolean push(String pathToRepository);
	boolean push(String pathToRepository, String username, String password);
	Map<String, String> status(String pathToRepository);
}
