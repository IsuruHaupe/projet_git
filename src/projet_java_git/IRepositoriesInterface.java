package projet_java_git;

import java.util.Map;

public interface IRepositoriesInterface {

	Map<String, String> getState(String... pathToRepositories);
	Map<String, Boolean> pull(String... pathToRepositories);
	Map<String, Boolean> push(String... pathToRepositories);
}
