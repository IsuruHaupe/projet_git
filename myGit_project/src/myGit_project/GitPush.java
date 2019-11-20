package myGit_project;

import java.io.Console;
import java.util.Scanner;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitPush {
	private Repository reposGit;
	
	public void gitPush(String commitMessage) throws InvalidRemoteException, TransportException, GitAPIException {
		Console console = System.console();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username");
		String username = sc.nextLine();  // Read user input
		String enteredPassword = new String(console.readPassword("Please enter your password: "));
	    Git git = new Git(this.getReposGit()); 
		DirCache index = git.add().addFilepattern(".").call();
		RevCommit commit = git.commit().setMessage(commitMessage).call();
	    PushCommand pushCommand = git.push();
	    pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, enteredPassword));
	    pushCommand.call();
	}
	
	public Repository getReposGit() {
		return this.reposGit;
	}
	
	public void setReposGit(Repository git) {
		this.reposGit = git;
	}
}
