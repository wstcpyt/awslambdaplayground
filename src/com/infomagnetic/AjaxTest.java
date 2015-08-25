package com.infomagnetic;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.infomagnetic.inverseproblem.CEA.CEAlgorithm;

import java.io.IOException;

/**
 * Created by yutongpang on 8/24/15.
 */
public class AjaxTest
{
    public String myHandler(int myCount, Context context) throws IOException {
        CEAlgorithm ceAlgorithm = new CEAlgorithm(3, 4);
        Gson gson = new Gson();
        String json = gson.toJson(ceAlgorithm.population);
        return json;
    }
}
