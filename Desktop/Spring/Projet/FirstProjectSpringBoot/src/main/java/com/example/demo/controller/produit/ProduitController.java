package com.example.demo.controller.produit;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.produitBatchLauncher;
import com.example.demo.ProductUtils.ProductResponseMessage;
import com.example.demo.entity.ImagesProduit;
import com.example.demo.entity.MapLocalisationProduitFournisseur;
import com.example.demo.entity.Produit;
import com.example.demo.services.produit.IImagesProduitService;
import com.example.demo.services.produit.IProduitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.batch.core.repository.JobRestartException;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Api(tags="Produit Management")
@RequestMapping("/produit")
@Slf4j
public class ProduitController {
	
	@Autowired
	IProduitService produitService;

	@Autowired
	IImagesProduitService imagesProduitService;
	
	@Autowired
    private produitBatchLauncher batchLauncher;
	
	
	//  upload multi images
	/*@DeleteMapping("/deletetemp")
	 @ResponseBody
	 @ApiOperation(value = "Supprimer temp image produit")
	 public void removeTempImagesProduit() {
		imagesProduitService.deleteAll();
	 } */
	
	
	
	 @PostMapping("/upload")
	 @ResponseBody
	 @ApiOperation(value = "upload image un produit")
	  public ResponseEntity<ProductResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	imagesProduitService.save(file);
	    	
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ProductResponseMessage(message));
	    }
	  }

	  @GetMapping("/files")
	  @ResponseBody
		 @ApiOperation(value = "get images  produit")
	  public ResponseEntity<List<ImagesProduit>> getListFiles() {
	    List<ImagesProduit> fileInfos = imagesProduitService.loadAll().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(ProduitController.class, "getFile", path.getFileName().toString()).build().toString();

	      return new ImagesProduit(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("/files/{filename:.+}")
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = (Resource) imagesProduitService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	  @GetMapping("/filesM")
	  @ResponseBody
		 @ApiOperation(value = "get images  produit M")
	  public ResponseEntity<List<ImagesProduit>> getListFilesM() {
	    List<ImagesProduit> fileInfos = imagesProduitService.loadAllM().map(path -> {
	      String filename = path.getFileName().toString();
	      String url = MvcUriComponentsBuilder
	          .fromMethodName(ProduitController.class, "getFileM", path.getFileName().toString()).build().toString();

	      return new ImagesProduit(filename, url);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	  }

	  @GetMapping("/filesM/{filename:.+}")
	  public ResponseEntity<Resource> getFileM(@PathVariable String filename) {
	    Resource file = (Resource) imagesProduitService.loadM(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	// end upload multi images
	// http://localhost:8081/SpringMVC/produit/add-produit
		 @PostMapping("/add-produit")
		 @ResponseBody
		 @ApiOperation(value = "Ajouter un produit")
		 public Produit addProduit(@RequestBody Produit p)
		{	// boolean isExist = new File(context.getRealPath("")).exists();
			 imagesProduitService.savefromtemptomain();
			 Produit produit = produitService.addProduit(p,p.getFournisseur().getId() );
		 return produit;
		}
		 
		// http://localhost:8081/SpringMVC/produit/remove-produit/{produit-id}
		 @DeleteMapping("/remove-produit/{produit-id}")
		 @ResponseBody
		 @ApiOperation(value = "Supprimer un produit")
		 public void removeProduit(@PathVariable("produit-id") Long produitId) {
			 produitService.deleteProduit(produitId);
		 }    
		 
		// http://localhost:8081/SpringMVC/produit/modify-produit
		 @PutMapping("/modify-produit")
		 @ResponseBody
		 @ApiOperation(value = "Modifier un produit")	 
		 public Produit modifyProduit(@RequestBody Produit produit) {
		 return produitService.modProduit(produit); 
		 }
		 
		// http://localhost:8081/SpringMVC/produit/retrieve-all-produits
		 @GetMapping("/retrieve-all-produits")
		 @ResponseBody
		 @ApiOperation(value = "Récupérer la liste des produits")
		 public List<Produit> getProduits() {
		 List<Produit> listProduits = produitService.retrieveAllProduits() ;
		 return listProduits;
		 }
		 
		// http://localhost:8081/SpringMVC/produit/retrieve-produit/8
		 @GetMapping("/retrieve-produit/{produit-id}")
		 @ResponseBody
		 @ApiOperation(value = "Récupérer un produit avec produitId")
		 public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
		 return produitService.retrieveProduit(produitId);
		 }
		 
		// http://localhost:8081/SpringMVC/produit/modifier-produit-fournisseur
		@PostMapping("/modifier-produit-fournisseur/{produit-id}/{fournisseur-id}")
		@ResponseBody
		@ApiOperation(value = "Modifier fournisseur d'un produit")	 
		public Produit modifyProduitFournisseur(@PathVariable("fournisseur-id") String fournisseurId,@PathVariable("produit-id")  Long produitId) {
		return produitService.assignFournisseurToProduit(fournisseurId , produitId); 
		}
		
		//
		 @GetMapping("/retrieve-all-produits-created-between/{dateDebut}/{dateFin}")
		 @ResponseBody
		 @ApiOperation(value = "retrieve-all-produits-created-between")
		 public List<Produit> getProduitsCreatedBetween(@PathVariable("dateDebut") Date dateDebut,@PathVariable("dateFin") Date dateFin) {
		 List<Produit> listProduits = produitService.getProductsCreatedBetween(dateDebut, dateFin) ;
		 return listProduits;
		 }
		 
		 @GetMapping("/retrieve-all-produits-revenue-between/{dateDebut}/{dateFin}")
		 @ResponseBody
		 @ApiOperation(value = "retrieve-all-produits-revenue-between")
		 public float getProductsRevenueBetween(@PathVariable("dateDebut") Date dateDebut,@PathVariable("dateFin") Date dateFin) {
			 float totalProduits = produitService.getProductsRevenueBetween(dateDebut, dateFin) ;
		 return totalProduits;
		 }
		 
		 @GetMapping("/retrieve-all-produits-limit-consommation-between/{dateDebut}/{dateFin}")
		 @ResponseBody
		 @ApiOperation(value = "retrieve-all-produits-limit-consommation-between")
		 public List<Produit> getProduitsLimitBetween(@PathVariable("dateDebut") Date dateDebut,@PathVariable("dateFin") Date dateFin) {
		 List<Produit> listProduits = produitService.getProductsLimiteConsommation(dateDebut, dateFin) ;
		 return listProduits;
		 }
		 
		 @GetMapping("/retrieve-all-produits-stats")
		 @ResponseBody
		 @ApiOperation(value = "retrieve-all-produits-stats")
		 public Map<String, Integer> statistiqueProduit() {
			 Map<String, Integer> listProduits = produitService.statistiqueProduit();
		 return listProduits;
		 }
		
		 //export csv
		 @GetMapping("/download-geomap-csv")
		  public ResponseEntity<Resource> getGeoMapCsvFile() {
		    String filename = "GeoMapLocaions.csv";
		    InputStreamResource file = new InputStreamResource(produitService.loadGeoMapCSV());

		    return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/csv"))
		        .body(file);
		  }
		 //load csv
		 @PostMapping("/uploadcsv")
		 @ResponseBody
		 @ApiOperation(value = "upload csv geo loca  produits")
		  public ResponseEntity<ProductResponseMessage> uploadCSVFile(@RequestParam("file") MultipartFile file) {
		    String message = "";
		    try {
		    	imagesProduitService.savecsv(file);
		    	 log.info("Batch produit  programmé");
		         try {batchLauncher.run();} 
		         catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException
		 				| JobInstanceAlreadyCompleteException e) {
		 			log.info("ERROR!!");
		 		}
		      message = "Uploaded the file successfully: " + file.getOriginalFilename();
		      return ResponseEntity.status(HttpStatus.OK).body(new ProductResponseMessage(message));
		    } catch (Exception e) {
		      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
		      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ProductResponseMessage(message));
		    }
		  }
		 //export pdf
		 @GetMapping(value = "/download-geomap-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
public ResponseEntity<InputStreamResource> produitsReport() throws IOException {
HttpHeaders headers = new HttpHeaders();
headers.add("Content-Disposition", "attachment; filename=GeoMapLocaions.pdf");
return ResponseEntity.ok().headers(headers).contentType
                    (MediaType.APPLICATION_PDF)
     .body(new InputStreamResource(produitService.loadGeoMapPDF()));
}
		 
}
