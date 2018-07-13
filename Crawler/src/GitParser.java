import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class GitParser extends Parser {

    public GitParser(String URL) {
	super(URL);
    }

    public GitProfile parse() {

	// append user nickname to api URL
	String getUserURL = "https://api.github.com/users" + URL.substring(URL.lastIndexOf('/'));
	String userResponse = request(getUserURL);

	// change URL to get user repositories
	String getReposURL = getUserURL + "/repos";
	String reposResponse = request(getReposURL);

	GitProfile profile = parseGit(userResponse, reposResponse);
	return profile;
    }

    private GitProfile parseGit(String userResponse, String reposResponse) {
	GitProfile gitProfile = null;

	try {
	    // parse user info
	    Object objUser = new JSONParser().parse(userResponse);
	    JSONObject jsonObjUser = (JSONObject) objUser;

	    String login = (String) jsonObjUser.get("login");
	    String name = (String) jsonObjUser.get("name");
	    String company = (String) jsonObjUser.get("company");
	    String location = (String) jsonObjUser.get("location");

	    // parse repositories
	    Object objRepos = new JSONParser().parse(reposResponse);
	    JSONArray jsonArrayRepos = (JSONArray) objRepos;

	    // get lists of all user repositories
	    List<String> repoNames = new ArrayList<String>();
	    List<Long> repoStars = new ArrayList<Long>();
	    List<String> repoLangs = new ArrayList<String>();

	    for (int i = 0; i < jsonArrayRepos.size(); i++) {
		JSONObject obj = (JSONObject) jsonArrayRepos.get(i);

		repoNames.add((String) obj.get("name"));
		repoStars.add((Long) obj.get("stargazers_count"));
		repoLangs.add((String) obj.get("language"));
	    }

	    // get user's most common language
	    String commonLang = mostCommon(repoLangs);

	    // get user's most popular repository
	    Long popularRepoStars = Collections.max(repoStars);
	    int i = repoStars.indexOf(popularRepoStars);
	    String popularRepoName = repoNames.get(i);
	    String popularRepoLang = repoLangs.get(i);

	    // create a profile
	    gitProfile = new GitProfile(login, name, company, location, commonLang, popularRepoName, popularRepoStars,
		    popularRepoLang);

	} catch (ParseException e) {
	    System.out.println(e.getMessage());
	}

	return gitProfile;
    }

    // returns most common element of list
    private <T> T mostCommon(List<T> list) {

	// create a map containing elements and its frequencies
	Map<T, Integer> map = new HashMap<>();

	for (T item : list) {
	    if (map.get(item) == null) {
		map.put(item, 1);
	    } else {
		int num = map.get(item);
		map.put(item, ++num);
	    }
	}

	// find element with max frequency
	T maxElement = null;
	int maxFrequency = 0;

	Set<Entry<T, Integer>> entrySet = map.entrySet();

	for (Entry<T, Integer> entry : entrySet) {
	    if (entry.getValue() > maxFrequency) {
		maxElement = entry.getKey();
		maxFrequency = entry.getValue();
	    }
	}

	return maxElement;
    }

}
