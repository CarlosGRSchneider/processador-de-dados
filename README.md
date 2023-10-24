# processador-de-dados
Este projeto foi criado para um artigo que escrevi para o Medium, de como levantar uma arquitetura de processamento de arquivos em nuvem. O artigo tem duas partes que podem ser lidas aqui.

https://medium.com/@carlosguilherme.schneider/aws-montar-automa%C3%A7%C3%A3o-de-servi%C3%A7o-de-processamento-de-dados-parte-1-9775c22d5abc

https://medium.com/@carlosguilherme.schneider/aws-montar-automa%C3%A7%C3%A3o-de-servi%C3%A7o-de-processamento-de-dados-parte-2-c3e7cdb090ad

Existem 2 repositorios de código que foram usados no exemplo, e este é o responsável por buscar a massa de dados do bucket de entrada, processa-los, e gerar um arquivo de relatórios no bucket de saída, bem como enviar uma mensagem de processamento para um tópico SNS.
Para que o projeto funcione dentro da função Lambda, é necessário substituir o nome dos buckets nas classes S3Downloader(bucket da massa de dados) e S3Uploader(bucket de relatorios), bem como colocar o nome do arn na classe SnsTopic
