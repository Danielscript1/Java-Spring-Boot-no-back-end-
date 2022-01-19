package com.testeweb.course.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import com.testeweb.course.services.exception.FileException;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
@Service
public class ImageService {
	
	 public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		 //primeiro vamos pegar a extensão do arquivo
		 String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		 //teste de verificação
		 if(!"png".equals(ext) && !"jpg".equals(ext)) {
			 throw new FileException("Somente extensão png e jpg permitido");
		 }
		 //tentar obter o buffer de imagem
		 try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			//funcao para conveter para jpg
			if("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			
			throw new FileException("erro ao ler o arquivo");
		}
		 
		
	 }


		public BufferedImage pngToJpg(BufferedImage img) {
			BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
			return jpgImage;
		}

		public InputStream getInputStream(BufferedImage img, String extension) {
			try {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(img, extension, os);
				return new ByteArrayInputStream(os.toByteArray());
			} catch (IOException e) {
				throw new FileException("Erro ao ler arquivo");
			}
		}
}
