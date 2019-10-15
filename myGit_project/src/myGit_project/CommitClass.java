package myGit_project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;

public class CommitClass {
  public static void main(String[] args)
      throws IOException, NoFilepatternException, GitAPIException {

    Path repoPath = Paths.get("/Users/isuruhaupe/Desktop/Imt_Atlantique/ACDC/projet_ACDC/test");
    if (!Files.exists(repoPath)) {
      try (Git git = Git.init().setDirectory(repoPath.toFile()).call()) {
      }
    }

    try (Git git = Git.open(repoPath.toFile())) {

      Files.writeString(repoPath.resolve("file1.md"), "Hello World 1");
      git.add().addFilepattern("file1.md").call();
      git.commit().setMessage("create file1").setAuthor("author", "author@email.com")
          .call();

      Files.writeString(repoPath.resolve("file2.md"), "Hello World 2");
      git.add().addFilepattern(".").call();
      git.commit().setMessage("create file2").setAuthor("author", "author@email.com")
          .call();

      // Update
      Files.writeString(repoPath.resolve("file1.md"), "Hello Earth 1");
      git.commit().setAll(true).setMessage("update file1")
          .setAuthor("author", "author@email.com").call();

      // Delete
      Files.deleteIfExists(repoPath.resolve("file2.md"));
      git.commit().setAll(true).setMessage("delete file2")
          .setAuthor("IsuruHaupe", "isuru.liyanage.20@gmail.com").call();

      // or
      git.rm().addFilepattern("file2.md").call();
      git.commit().setMessage("delete file2").setAuthor("IsuruHaupe", "isuru.liyanage.20@gmail.com")
          .call();

    }

  }
}
