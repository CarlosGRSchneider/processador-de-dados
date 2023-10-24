package amazonenv;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

import java.time.LocalDate;

public class SnsTopic {


    public static void enviaMensagemDeSucesso(AmazonSNS snsClient) {
//      Colocar o seu topico aqui
        String snsTopicArn = "o nome do seu topico vem aqui";
        String mensagem = "Um novo relatorio foi publicado em " + LocalDate.now() + "\n\n Ele se encontra disponivel no bucket-relatorios-gerados.";


        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(snsTopicArn)
                .withMessage(mensagem);

        snsClient.publish(publishRequest);
    }
}
