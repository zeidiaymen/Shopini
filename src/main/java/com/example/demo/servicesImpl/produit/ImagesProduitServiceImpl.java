package com.example.demo.servicesImpl.produit;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.repository.produit.IImagesProduitRepository;
import com.example.demo.services.produit.IImagesProduitService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagesProduitServiceImpl implements IImagesProduitService{

	@Autowired
	IImagesProduitRepository imagesProduitRepository;
	
	  private final Path root = Paths.get("ProductImagesUploads");
	  private final Path roottemp = Paths.get("tempProductImagesUploads");
	  private final Path rootcsv = Paths.get("ProductGeoLocalisationscsv");
	  private final Path rootcsv2 = Paths.get("src","main","resources");
	  private final Path rootdelcsv = Paths.get("src/main/resources/GeoMapLocations.csv");
	  private final Path rootdelcsv2 = Paths.get("ProductGeoLocalisationscsv/GeoMapLocations.csv");

	  @Override
	  public void init() {
	    try {
	    	if(!Files.exists(root) ){Files.createDirectory(root);}
	    	if(!Files.exists(roottemp) ){Files.createDirectory(roottemp);}
	    	if(!Files.exists(rootcsv) ){Files.createDirectory(rootcsv);}

	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }

	  @Override
	  public void save(MultipartFile file) {
	    try {
		      Files.copy(file.getInputStream(), this.roottemp.resolve(file.getOriginalFilename()));
		     // Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	  @Override
	  public void savecsv(MultipartFile file) {
	    try {
	    	Files.deleteIfExists(this.rootdelcsv);
	    	Files.deleteIfExists(this.rootdelcsv2);
	    	Files.copy(file.getInputStream(), this.rootcsv.resolve(file.getOriginalFilename()));
		    Files.copy(file.getInputStream(), this.rootcsv2.resolve(file.getOriginalFilename()));
		    
		    //  Files.copy(file.getInputStream(), this.rootcsv.resolve(file.getOriginalFilename()));
		     // Files.copy(file.getInputStream(), this.rootcsv2.resolve(file.getOriginalFilename()));
		     // Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }

	  }
	  @Override
	  public Stream<Path> loadAll() {
	    try {
	      return Files.walk(this.roottemp, 1).filter(path -> !path.equals(this.roottemp)).map(this.roottemp::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }

	  @Override
	  public Resource load(String filename) {
	    try {
	      Path file = roottemp.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	  
	  @Override
	  public void deleteAll() {
	    try {
			FileUtils.cleanDirectory(roottemp.toFile() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	  }
	  
	  @Override
	  public void savefromtemptomain() {
		  try {
		//	Files.copy(this.roottemp, this.root);
			FileUtils.copyDirectory(this.roottemp.toFile(), this.root.toFile());
			FileUtils.cleanDirectory(roottemp.toFile() );

		} catch (IOException e) {
			// TODO Auto-generated catch block
		      throw new RuntimeException("Could not load the files!");
		}
	  }
	  
	  
	  
	  @Override
	  public Stream<Path> loadAllM() {
	    try {
	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not load the files!");
	    }
	  }
	  
	  @Override
	  public Resource loadM(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());

	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }



	 


	  
	
}
