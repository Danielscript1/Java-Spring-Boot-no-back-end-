package com.testeweb.course.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			//esse metodo consegue estrair o nome do arquivo q foi enviado
			String fileName = multipartFile.getOriginalFilename();
			//leitura apartir de uma origem
			InputStream is = multipartFile.getInputStream();
			//instancia um string, o string coorrespondente a informação do tipo de arquivo q foi enviado
			String contentType = multipartFile.getContentType();
			return uploadFile(is,fileName,contentType);
			
			} catch (IOException e) {
				throw new RuntimeException("erro de IO"+e.getMessage());
			}
			
			
			
	}
	
	public URI uploadFile(InputStream is,String fileName,String contentType) {
		/*Representa os metadados do objeto que são armazenados com o Amazon S3.
		 *  Isso inclui metadados personalizados fornecidos pelo usuário, 
		 *  bem como os cabeçalhos HTTP padrão que o Amazon S3 envia e recebe
		 *  (Content-Length, ETag, Content-MD5 etc.).*/
		try {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		LOG.info("Iniciando upload");
		s3client.putObject(bucketName, fileName, is, meta);
		LOG.info("Upload finalizado");
		//tenho que retorna funcao do tippo url do java,porem eu quero do tipo uri,então faço a conversao
	
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("erro ao converter url para uri");
		}
	}
	
	
}

