package com.example.demo.services.produit;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IImagesProduitService {
	
	public void init();

	  public void save(MultipartFile file);
	  public void savecsv(MultipartFile file);

	  public Resource load(String filename);
	  public Resource loadM(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();
	  public Stream<Path> loadAllM();
	  
	  public void savefromtemptomain();
	  

}
