package br.com.localhost8080.findyourbus.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Class to call the Rest Services.
 * TODO: The work here is manual, it should be changed by a lib like android-annotations, spring, etc.
 */
public class BusQueriesRestClient {

    /**
     * Username:Password encoded in Base64 (https://www.base64encode.org/)
     */
    private String authEncoded = "V0tENE43WU1BMXVpTThWOkR0ZFR0ek1MUWxBMGhrMkMxWWk1cEx5VklsQVE2OA==";

    private String paramAuthKey = "Authorization";
    private String paramAuthValue = "Basic " + authEncoded;

    private String param1key = "X-AppGlu-Environment";
    private String param1value = "staging";

    private String param2key = "Accept";
    private String param2value = "application/json";

    private String param3key = "Content-Type";
    private String param3value = "application/json";

    private String requestMethod = "POST";

    private Charset charset = Charset.forName("UTF-8");

    public String doPost(String urlString, String body) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty(paramAuthKey, paramAuthValue);
            conn.setRequestProperty(param1key, param1value);
            conn.setRequestProperty(param2key, param2value);
            conn.setRequestProperty(param3key, param3value);
            conn.setRequestMethod(requestMethod);

            conn.setDoOutput(true);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(body);
            out.close();

            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String jsonReturn = br.readLine();
            return jsonReturn;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return null;
    }
}
