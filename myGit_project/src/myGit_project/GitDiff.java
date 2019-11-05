package myGit_project;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class GitDiff {
	private Repository reposGit;
	
	public String gitDiff() throws RevisionSyntaxException, AmbiguousObjectException, IncorrectObjectTypeException, IOException {
		String s = "";
		ObjectReader reader = this.getReposGit().newObjectReader();
		CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
		ObjectId oldTree = this.getReposGit().resolve( "HEAD^{tree}" );
		oldTreeIter.reset( reader, oldTree );
		CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
		ObjectId newTree = this.getReposGit().resolve( "HEAD~1^{tree}" );
		newTreeIter.reset( reader, newTree );
		DiffFormatter df = new DiffFormatter( new ByteArrayOutputStream() );
		df.setRepository(this.getReposGit());
		List<DiffEntry> entries = df.scan( oldTreeIter, newTreeIter );
		for( DiffEntry entry : entries ) {
		  s = s + entry;
		}
		return s;
	}
	
	public Repository getReposGit() {
		return this.reposGit;
	}
	
	public void setReposGit(Repository git) {
		this.reposGit = git;
	}
}
