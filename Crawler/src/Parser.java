import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Semaphore;

abstract class Parser {
	protected String URL;

	private int maxParallelRequests = 1;
	private int requestDelayMs = 500;
	private final Semaphore available;

	public Parser(String URL) {
		this.URL = URL;

		// get environment variables
		try {
			maxParallelRequests = Integer.parseInt(System.getenv("maxParallelRequests"));
			requestDelayMs = Integer.parseInt(System.getenv("requestDelayMs"));
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		available = new Semaphore(maxParallelRequests, true);
	}

	protected String request(String requestURL) {
		String response = "";

		try {
			available.acquire();
			System.out.println("Sending http request: " + requestURL + "\n");

			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				response += line;
			}

			in.close();
			connection.disconnect();
			Thread.sleep(requestDelayMs);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			available.release();
		}

		return response;
	}

	public abstract Profile parse();

}
