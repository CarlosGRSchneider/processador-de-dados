package amazonenv;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import processador.ProcessadorDeDados;

import java.util.List;

public class Main {

    public String executeLambda() {

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("sa-east-1").build();
        AmazonSNS snsClient = AmazonSNSClientBuilder.defaultClient();

        S3Downloader downloader = new S3Downloader(s3Client);
        List<String> linhasCsv = downloader.downloadFileFromS3();

        if (linhasCsv != null) {

            ProcessadorDeDados processador = new ProcessadorDeDados();
            String csv = processador.processaDados(linhasCsv);

            S3Uploader uploader = new S3Uploader(s3Client);
            uploader.upload(csv);

            SnsTopic.enviaMensagemDeSucesso(snsClient);

            return "Executado";
        }
        return "Não executado.";
    }
}
