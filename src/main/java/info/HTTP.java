package info;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTP {
    private HttpClient httpclient;
    private HttpPost httppost;
    private String ipPort;
    public HTTP(String ipPort) {
        this.ipPort = ipPort;
    }

    public void post(String name, int score) {
        httpclient = HttpClients.createDefault();
        httppost = new HttpPost(ipPort);
        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("score", String.valueOf(score)));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            //Execute and get the response.
            httpclient.execute(httppost);
            EntityUtils.consume(httppost.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
