package myGit_project;

import java.util.List;
import org.eclipse.jgit.lib.Repository;

public interface IMyGit {
	public List<Repository> getRepositories(boolean verbose);
	public List<Repository> getOutdatedRepositories(boolean verbose);
	public String getDiffs(Repository repository, boolean verbose);
	public List<String> getAllDiffs(boolean verbose);
	public String update(Repository repository, boolean verbose);
	public List<String> updateAll(boolean verbose);
	public void pushChange(Repository repository, String commitMessage, boolean verbose);
	public void pushChangeAll(String commitMessage, boolean verbose);
}
