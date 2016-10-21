package org.telegram.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Weather service
 * @date 20 of June of 2015
 */
public class JenkinsService {
  private static volatile BotLogger log = BotLogger.getLogger(JenkinsService.class.getName());

  private static final String BASE_URL = "http://54.152.74.178:10000/";
  private static final String JOBS_URL = BASE_URL + "api/json?depth=1";

  private static volatile JenkinsService instance; ///< Instance of this class

  private Map<String, Emoji> iconLookup = new HashMap<>();

  private JenkinsService() {
    iconLookup.put("icon-health-00to19", Emoji.HIGH_VOLTAGE_SIGN);
    iconLookup.put("icon-health-20to39", Emoji.SUN_BEHIND_CLOUD);
    iconLookup.put("icon-health-40to59", Emoji.CLOUD);
    iconLookup.put("icon-health-60to79", Emoji.UMBRELLA_WITH_RAIN_DROPS);
    iconLookup.put("icon-health-80plus", Emoji.SUN_WITH_FACE);
  }

  /**
   * Singleton
   *
   * @return Return the instance of this class
   */
  public static JenkinsService getInstance() {
    JenkinsService currentInstance;
    if (instance == null) {
      synchronized (JenkinsService.class) {
        if (instance == null) {
          instance = new JenkinsService();
        }
        currentInstance = instance;
      }
    } else {
      currentInstance = instance;
    }
    return currentInstance;
  }

  public Map<String, Emoji> getJobs() {
    Map<String, Emoji> result = null;

    try {
      CloseableHttpClient client = HttpClientBuilder.create().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
      HttpGet request = new HttpGet(JOBS_URL);

      CloseableHttpResponse response = client.execute(request);
      HttpEntity ht = response.getEntity();

      BufferedHttpEntity buf = new BufferedHttpEntity(ht);
      String responseString = EntityUtils.toString(buf, "UTF-8");

      JSONObject jsonObject = new JSONObject(responseString);


      JSONArray jobs = jsonObject.getJSONArray("jobs");

      result = new HashMap<>();
      for (int i = 0; i < jobs.length(); i++) {
        JSONObject job = jobs.getJSONObject(i);
        JSONArray healthReports = job.getJSONArray("healthReport");
        if (healthReports.length() > 0) {
          JSONObject healthReport = healthReports.getJSONObject(0);
          result.put(job.getString("name"), iconLookup.get(healthReport.getString("iconClassName")));

        }

      }
    } catch (Exception e) {
      log.error(e);
    }

    return result;
  }


}
