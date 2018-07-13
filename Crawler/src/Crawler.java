import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    public static void main(String[] args) {
	// get list of URLs from file
	List<String> urlList = readFile("src/URLs.txt");
	List<GitProfile> profiles = new ArrayList<GitProfile>();

	// parse each URL and save result profile to list
	for (String url : urlList) {
	    GitParser parser = new GitParser(url);
	    profiles.add(parser.parse());
	}

	// print each profile info
	for (GitProfile prof : profiles) {
	    if (prof != null) {
		System.out.println(prof.getFullInfo() + "\n");
	    }
	}

    }

    private static List<String> readFile(String fileName) {
	List<String> urls = new ArrayList<String>();

	try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	    String str;

	    while ((str = reader.readLine()) != null) {
		urls.add(str);
	    }

	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}

	return urls;
    }
}
