package myGit_project;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.RemoteAddCommand;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.RefNotAdvertisedException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.FileTreeIterator;

public class GitPull {
	private Repository reposGit;
	
	public String gitPull() throws WrongRepositoryStateException, InvalidConfigurationException, InvalidRemoteException, CanceledException, RefNotFoundException, RefNotAdvertisedException, NoHeadException, TransportException, GitAPIException {
		PullResult pull = new Git(this.getReposGit()).pull().call();
		return pull.toString();
	}
	
	
	public void openRepos(String reposPath) throws IOException {
		File repos = new File(reposPath);
		try {
			Git git = Git.open(repos);
			this.setReposGit(git.getRepository());
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}		
	}
	
	public Repository getReposGit() {
		return this.reposGit;
	}
	
	public void setReposGit(Repository git) {
		this.reposGit = git;
	}
}
