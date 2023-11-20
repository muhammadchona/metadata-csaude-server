package mz.org.csaude.metadata.restUtils

import grails.util.Environment
import mz.org.csaude.metadata.server.Server
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPatch
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.grails.web.json.JSONArray
import org.grails.web.json.JSONObject

class RestClient {

    RestClient() {

    }

    static def requestGetDataOnProvincialServerClient(String urlPath) {
        def currentEnvironment = Environment.current.name
        def type = null
        println("Current environment: $currentEnvironment")
        if (currentEnvironment == Environment.DEVELOPMENT.name) {
          type = 'TEST'
        } else if (currentEnvironment == Environment.PRODUCTION.name) {
            type = 'PROD'
        }
        def server = Server.findWhere(destination: 'CENTRAL_TOOL',type:type)
        String restUrl = server.getUrlPath()+server.getPort()+urlPath
        String result = ""
        int code = 200
        try {
          //  println(restUrl)
            URL siteURL = new URL(restUrl)
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection()
         //   connection.setRequestProperty("Authorization", "Bearer " + PostgrestAuthenticationUtils.getJWTPermission(restUrlBase, provincialServer.getUsername(), provincialServer.getPassword(), provincialServer.destination))
            connection.setRequestMethod("GET")
            connection.setRequestProperty("Content-Type", "application/json; utf-8")
            connection.setDoInput(true)
            connection.setDoOutput(true)
//            connection.setConnectTimeout(3000)
            // Send post request
            connection.connect()
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                String inputLine
              ///  List response = new ArrayList<>()
                StringBuffer response = new StringBuffer()
                while ((inputLine = input.readLine()) != null) {
                    response.append(inputLine)
                }
                input.close()

                // print result
              String responseStr = response.toString();

                JSONObject jsonResponse = new JSONObject(responseStr);
                JSONObject resultJson = jsonResponse.getJSONObject("result");
                JSONArray contentArray = resultJson.getJSONArray("content");
                println(new JSONArray(contentArray))
                return new JSONArray(contentArray)
            } else {
                println("GET request not worked")
                println(new JSONObject("{\"sessionId\":null,\"authenticated\":null}"))
                return new JSONObject("{\"sessionId\":null,\"authenticated\":null}")
            }

//            connection.connect()
            code = connection.getResponseCode()
            connection.disconnect()
            if (code == 201) {
                result = "-> Green <-\t" + "Code: " + code;
            } else {
                result = "-> Yellow <-\t" + "Code: " + code;
            }
        } catch (Exception e) {
            result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
        }
        return result
    }
}
