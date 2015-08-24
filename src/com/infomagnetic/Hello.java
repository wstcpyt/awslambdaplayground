package com.infomagnetic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.infomagnetic.inverseproblem.CEA.CEAlgorithm;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yutongpang on 8/22/15.
 */
public class Hello {
    private int populationLength = 10;
    private int individualPropertiesSize = 3;
    public String myHandler(int myCount, Context context) throws IOException {

        CEAlgorithm ceAlgorithm = new CEAlgorithm(individualPropertiesSize, populationLength);
        ceAlgorithm.initPopulation(-0.1, 0.1);

        String testarrayString = xVector.toString();
        AmazonS3 s3Client = new AmazonS3Client();
        ObjectMetadata metadata = new ObjectMetadata();

        InputStream in = IOUtils.toInputStream(testarrayString, "UTF-8");

        PutObjectRequest putObjectRequest = new PutObjectRequest("yutonginverseproblem", "test/hello.txt", in, metadata);
        s3Client.putObject(putObjectRequest);

        LambdaLogger logger = context.getLogger();
        logger.log("received new: ");
        return String.valueOf(1);
    }
}
