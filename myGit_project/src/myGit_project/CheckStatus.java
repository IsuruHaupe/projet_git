package myGit_project;

import java.io.File;
import java.io.IOException;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.errors.NoWorkTreeException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.lib.Repository;

public class CheckStatus {
	private Repository reposGit;
	
	
	public void checkStatusVerbose() throws NoWorkTreeException, GitAPIException {
		Status status = new Git(this.getReposGit()).status().call();
        System.out.println("Added: " + status.getAdded());
        System.out.println("Changed: " + status.getChanged());
        System.out.println("Conflicting: " + status.getConflicting());
        System.out.println("ConflictingStageState: " + status.getConflictingStageState());
        System.out.println("IgnoredNotInIndex: " + status.getIgnoredNotInIndex());
        System.out.println("Missing: " + status.getMissing());
        System.out.println("Modified: " + status.getModified());
        System.out.println("Removed: " + status.getRemoved());
        System.out.println("Untracked: " + status.getUntracked());
        System.out.println("UntrackedFolders: " + status.getUntrackedFolders());
        this.getReposGit().close();
	}
	
	public boolean checkStatus() throws NoWorkTreeException, GitAPIException {
		Status status = new Git(this.getReposGit()).status().call();
		return status.isClean();
	}
	
	public Repository getReposGit() {
		return this.reposGit;
	}
	
	public void setReposGit(Repository git) {
		this.reposGit = git;
	}
}
