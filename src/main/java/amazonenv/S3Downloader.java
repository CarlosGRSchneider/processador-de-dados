package amazonenv;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class S3Downloader {

    private AmazonS3 s3Client;

    public S3Downloader(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> downloadFileFromS3() {

        String nomeBucket = "bucket-dados";
        String nomeArquivoBucket = "massa-de-dados" + LocalDate.now() + ".csv";

        S3Object object = s3Client.getObject(new GetObjectRequest(nomeBucket, nomeArquivoBucket));
        try (InputStream leitorDeObjeto = object.getObjectContent()) {
            byte[] objetoEmBytes = IOUtils.toByteArray(leitorDeObjeto);
            String csvEmString = new String(objetoEmBytes);

            List<String> csvEmLinhas = Arrays.asList(csvEmString.split("\n"));

            return csvEmLinhas;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


