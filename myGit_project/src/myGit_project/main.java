package myGit_project;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;

public class main {

	public static void main(String[] args) throws IllegalStateException, GitAPIException, IOException {

		Path repoPath = Paths.get("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/test");

		try (Git git = Git.init().setDirectory(repoPath.toFile()).call()) {

		}

		InitCommand init = Git.init();
		init.setDirectory(repoPath.toFile());
		try (Git git = init.call()) {

		}

		try (Git git = Git.open(repoPath.toFile())) {

		}

	}

}