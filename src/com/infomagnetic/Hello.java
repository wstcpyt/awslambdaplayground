package com.infomagnetic;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.infomagnetic.inverseproblem.CEA.CEAlgorithm;
import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by yutongpang on 8/22/15.
 */
public class Hello {
    private final int numberofThread = 4;
    private final int populationLengthPerThread = 10;
    private int individualPropertiesSize = 3;
    public String myHandler(int myCount, Context context) throws IOException {

        CEAlgorithm ceAlgorithm = new CEAlgorithm(individualPropertiesSize, numberofThread* populationLengthPerThread);
        ceAlgorithm.initPopulation(-0.1, 0.1);
        PopulationInfo populationInfo = new PopulationInfo(ceAlgorithm.population, 0, numberofThread, populationLengthPerThread);
        AmazonS3 s3Client = new AmazonS3Client();
        ObjectMetadata metadata = new ObjectMetadata();
        byte[] byteStrem = SerializationUtils.serialize(populationInfo);
        ByteArrayInputStream input = new ByteArrayInputStream(byteStrem);

        PutObjectRequest putObjectRequest = new PutObjectRequest("yutonginverseproblem", "test/populationInfo.txt", input, metadata);
        s3Client.putObject(putObjectRequest);
        return "PopulationInfo INIT Sucess";
    }
}
