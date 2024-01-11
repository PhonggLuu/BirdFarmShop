/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.birdfarmshop.util;

import java.io.IOException;
import java.io.InputStream;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


/**
 *
 * @author Admin
 */
public class S3Utils {

    private static String BUCKET = "birdfarmshop";

    public static void uploadFile(String fileName, InputStream inputStream) throws IOException {
        try {
            AwsBasicCredentials credentials = AwsBasicCredentials.create(Constants.C3_KEY, Constants.C3_SECRET_KEY);
            S3Client client =  S3Client.builder()
                    .region(Region.AP_SOUTHEAST_1)
                     .credentialsProvider(StaticCredentialsProvider.create(credentials))
                    .build();
            PutObjectRequest request = PutObjectRequest.builder()
                                        .bucket(BUCKET)
                                        .key(fileName)
                                        .acl(ObjectCannedACL.PUBLIC_READ)
                                        .build();
            client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
