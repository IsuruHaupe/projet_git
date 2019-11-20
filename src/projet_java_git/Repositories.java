package projet_java_git;

import java.util.HashMap;
import java.util.Map;

public class Repositories implements IRepositoriesInterface{

	@Override
	public Map<String, String> getState(String... pathToRepositories) {
		Map<String, String> res = new HashMap<String, String>();
		Repository repo = new Repository();
		for(String elt : pathToRepositories) {
			res.put(elt, repo.getState(elt));
		}
		return res;
	}

	@Override
	public Map<String, Boolean> pull(String... pathToRepositories) {
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		Repository repo = new Repository();
		for(String elt : pathToRepositories) {
			res.put(elt, repo.pull(elt));
		}
		return res;
	}

	@Override
	public Map<String, Boolean> push(String... pathToRepositories) {
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		Repository repo = new Repository();
		for(String elt : pathToRepositories) {
			res.put(elt, repo.push(elt));
		}
		return res;
	}

}
