package projet_java_git;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Repository implements IRepositoryInterface {

	/**
	 * Add the desired files to the repository. Ignored files should not be added!
	 * 
	 * @param pathToRepository : the path to the repository without ".git".
	 * @param pathsToFiles     : the list of all the files that should be added.
	 */
	public void add(String pathToRepository, String... pathsToFiles) throws java.lang.Exception {
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute add command
			for(String elt : pathsToFiles) {
				p_stdin.write("git add " + elt);
				p_stdin.newLine();
				p_stdin.flush();
			}

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			System.out.println(s.nextLine());
		}
		s.close();
	}

	@Override
	public void commit(String pathToRepository, String message) {
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute commit command
			p_stdin.write("git commit -m '" + message + "'");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			System.out.println(s.next());
		}
		s.close();
	}

	@Override
	public String diffs(String pathToRepository) {
		String res = "";
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git diff HEAD");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			res += s.nextLine();
		}
		s.close();
		return res;
	}

	@Override
	public List<String> getNotAddedFiles(String pathToRepository) {
		List<String> res = new ArrayList<String>();
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute status command
			p_stdin.write("git status -s --porcelain");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			res.add(s.nextLine());
		}
		s.close();
		return res;
	}

	@Override
	public String getState(String pathToRepository) {
		String res = "";
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git status -sb --porcelain");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			res += s.nextLine();
		}
		s.close();
		return res;
	}

	@Override
	public boolean pull(String pathToRepository) {
		boolean worked = true;
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		String output;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git pull -q");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			output = s.nextLine();
			if (output != "") {
				System.out.println(output);
				worked = false;
			}
		}
		s.close();
		return worked;
	}

	public boolean pull(String pathToRepository, String username, String password) {
		boolean worked = true;
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		String output;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git pull -q");
			p_stdin.newLine();
			p_stdin.flush();

			// enter username
			p_stdin.write(username);
			p_stdin.newLine();
			p_stdin.flush();

			// enter password
			p_stdin.write(password);
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			output = s.nextLine();
			if (output != "") {
				System.out.println(output);
				worked = false;
			}
		}
		s.close();
		return worked;
	}

	@Override
	public boolean push(String pathToRepository) {
		boolean worked = true;
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		String output;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git push -q");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			output = s.nextLine();
			if (output != "") {
				System.out.println(output);
				worked = false;
			}
		}
		s.close();
		return worked;
	}

	@Override
	public boolean push(String pathToRepository, String username, String password) {
		boolean worked = true;
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		String output;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git push -q");
			p_stdin.newLine();
			p_stdin.flush();
			
			//add username
			p_stdin.write(username);
			p_stdin.newLine();
			p_stdin.flush();
			
			//add password
			p_stdin.write(password);
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			output = s.nextLine();
			if (output != "") {
				System.out.println(output);
				worked = false;
			}
		}
		s.close();
		return worked;
	}

	@Override
	public Map<String, String> status(String pathToRepository) {
		String output;
		Map<String, String> res = new HashMap<String, String>();
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		builder.redirectErrorStream(true);
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// get original working repository
			String originalRepository = this.getOriginalRepository();

			// change working directory
			p_stdin.write("cd " + pathToRepository);
			p_stdin.newLine();
			p_stdin.flush();

			// execute diff command
			p_stdin.write("git status -s --porcelain");
			p_stdin.newLine();
			p_stdin.flush();

			// reset working directory
			p_stdin.write("cd " + originalRepository);
			p_stdin.newLine();
			p_stdin.flush();

		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			output = s.next();
			System.out.println(output);
			switch (output) {
			case "??":
				res.put(s.next(), "UNTRACKED");
				break;
			case "D":
				res.put(s.next(), "DELETED");
				break;
			case "A":
				res.put(s.next(), "ADDED");
				break;
			case "C":
				res.put(s.next(), "COPIED");
				break;
			case "M":
				res.put(s.next(), "MODIFIED");
				break;
			case "R":
				res.put(s.next(), "RENAMED");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + output);
			}
		}
		s.close();
		return res;
	}

	private String getOriginalRepository() {
		String res = "";
		// initialisation
		ProcessBuilder builder = new ProcessBuilder("/bin/bash");
		Process p = null;
		try {
			p = builder.start();
		} catch (IOException e) {
			System.out.println(e);
		}
		// get stdin of shell
		BufferedWriter p_stdin = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));

		try {
			// single execution
			p_stdin.write("pwd");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// finally close the shell by execution exit command
		try {
			p_stdin.write("exit");
			p_stdin.newLine();
			p_stdin.flush();
		} catch (IOException e) {
			System.out.println(e);
		}

		// write stdout of shell (=output of all commands)
		Scanner s = new Scanner(p.getInputStream());
		while (s.hasNext()) {
			res = s.next();
		}
		s.close();
		return res;
	}
}
