package projet_java_git;

import java.util.List;
import java.util.Map;

public interface IRepositoryInterface {
	/**
	 * Add the desired files to the repository.
	 * Ignored files should not be added!
	 * @param pathToRepository : the path to the repository without ".git".
	 * @param pathsToFiles : the list of all the files that should be added.
	 * @throws java.lang.Exception : throws Exception
	 */
	void add(String pathToRepository, String... pathsToFiles) throws java.lang.Exception;
	/**
	 * Commit all added files. User should specify a message in order to allow that.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @param message : the message used in the commit.
	 * @throws java.lang.Exception : throws Exception
	 */
	void commit(String pathToRepository, String message) throws java.lang.Exception;
	/**
	 * Commit all added files. User should specify a message in order to allow that.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : the message used in the commit.
	 * @throws java.lang.Exception : throws Exception
	 */
	String diffs(String pathToRepository) throws java.lang.Exception;
	/**
	 * Get the files that can be added to the repository.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : a list containing all the files that can be added to the repository.
	 * @throws java.lang.Exception : throws Exception
	 */
	List<String> getNotAddedFiles(String pathToRepository) throws java.lang.Exception;
	/**
	 * Get the files that can be added to the repository.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : a list containing all the files that can be added to the repository.
	 * @throws java.lang.Exception : throws Exception
	 */
	String getState(String pathToRepository) throws java.lang.Exception;
	/**
	 * Pull changes from the remote.
	 * That can fail when user must be authenticated. 
	 * (Maybe throwing a custom exception like *NotAuthenticatedException* is more explicit than a boolean)
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : the success of the command.
	 * @throws java.lang.Exception : throws Exception
	 */
	boolean pull(String pathToRepository) throws java.lang.Exception;
	/**
	 * Pull changes from the remote using authentication with user and password.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @param username : the username that will be used in authentication.
	 * @param password : the password that will be used in authentication;
	 * @return : the success of the command
	 * @throws java.lang.Exception : throws Exception
	 */
	boolean pull(String pathToRepository, String username, String password) throws java.lang.Exception;
	/**
	 * Push all the committed changes to the remote.
	 * That can fail when user must be authenticated. 
	 * (Maybe throwing a custom exception like *NotAuthenticatedException*
	 * is more explicit than a boolean)
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : the success of the command.
	 * @throws java.lang.Exception : throws Exception
	 */
	boolean push(String pathToRepository) throws java.lang.Exception;
	/**
	 * Push all the committed changes to the remote using authentication with user and password.
	 * @param pathToRepository : the path to the repository without ".git".
	 * @param username : the username that will be used in authentication.
	 * @param password : the password that will be used in authentication;
	 * @return : the success of the command.
	 * @throws java.lang.Exception : throws Exception
	 */
	boolean push(String pathToRepository, String username, String password) throws java.lang.Exception;
	/**
	 * Get the status of the repository, this represent all the files 
	 * that are not currently added to the repository with their status.
	 * Status can be: - ADDED - COPIED - DELETED - MODIFIED - RENAMED - UNTRACKED
	 * @param pathToRepository : the path to the repository without ".git".
	 * @return : a list containing all the files that can be added to the repository with their status.
	 *  The first entry is the file path, the second is the status
	 * @throws java.lang.Exception : throws Exception
	 */
	Map<String, String> status(String pathToRepository) throws java.lang.Exception;
}
