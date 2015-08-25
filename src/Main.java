import com.amazonaws.util.json.JSONException;
import com.google.gson.Gson;
import com.infomagnetic.inverseproblem.CEA.CEAlgorithm;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by yutongpang on 8/24/15.
 */
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
        CEAlgorithm ceAlgorithm = new CEAlgorithm(3, 100);
        Gson gson = new Gson();
        String json = gson.toJson(ceAlgorithm.population);
        System.out.printf(json);
    }
}
