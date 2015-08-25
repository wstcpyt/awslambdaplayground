import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infomagnetic.inverseproblem.CEA.Individual;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yutongpang on 8/24/15.
 */
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
        Path path = Paths.get("src/hello.txt");
        byte[] data = Files.readAllBytes(path);
        Individual[][] population = SerializationUtils.deserialize(data);
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("pc9e740m8j.execute-api.us-east-1.amazonaws.com")
                .setPath("/test/mydemoresource")
                .build();

        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        ResponseHandler<JSONObject> rh = new ResponseHandler<JSONObject>() {
            @Override
            public JSONObject handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();
                if (statusLine.getStatusCode() >= 300){
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase()
                    );
                }
                if (entity == null) {
                    throw new ClientProtocolException("Response contains no content");
                }
                Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);
                return gson.fromJson(reader, JSONObject.class);
            }
        };
        Gson gson = new Gson();
        gson.toJson(1);

    }
}
