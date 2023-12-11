package amazonenv;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;

public class S3Uploader {

    private AmazonS3 s3Client;

    public S3Uploader(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public void upload(String relatorioEmString) {

        byte[] relatorioEmBytes = relatorioEmString.getBytes(StandardCharsets.UTF_8);

//      Colocar aqui o nome do seu bucket de saida
        String bucketName = "o nome do seu bucket de saida do relatorio aqui";

        try {

            String chaveDoArquivo = "relatorio" + LocalDate.now(ZoneId.of("GMT")) + ".txt";

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(relatorioEmBytes.length);
            metadata.setContentType("text/plain");

            PutObjectRequest request = new PutObjectRequest(bucketName, chaveDoArquivo, new ByteArrayInputStream(relatorioEmBytes), metadata);
            s3Client.putObject(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
