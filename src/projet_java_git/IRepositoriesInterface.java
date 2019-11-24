package projet_java_git;

import java.util.Map;

public interface IRepositoriesInterface {
	/**
	 * Get the states of the given repositories. It can be: - AHEAD - BEHIND - UP_TO_DATE
	 * @param pathToRepositories : the paths to all the desired repositories without ".git".
	 * @return : a map describe the current state of all given repositories.
	 * @throws java.lang.Exception : throws errors
	 */
	Map<String, String> getState(String... pathToRepositories) throws java.lang.Exception;
	/**
	 * Pull changes from the remote.
	 * That can fail when user must be authenticated. 
	 * (Maybe throwing a custom exception like *NotAuthenticatedException* is more explicit than a boolean)
	 * @param pathToRepositories : the paths to the all the desired repositories without ".git".
	 * @return : a map describing the success of the command on each repository.
	 * @throws java.lang.Exception : throws errors
	 */
	Map<String, Boolean> pull(String... pathToRepositories) throws java.lang.Exception;
	/**
	 * Get the states of the given repositories. It can be: - AHEAD - BEHIND - UP_TO_DATE
	 * @param pathToRepositories : the paths to all the desired repositories without ".git".
	 * @return : a map describe the current state of all given repositories.
	 * @throws java.lang.Exception : throws errors
	 */
	Map<String, Boolean> push(String... pathToRepositories) throws java.lang.Exception;
}
