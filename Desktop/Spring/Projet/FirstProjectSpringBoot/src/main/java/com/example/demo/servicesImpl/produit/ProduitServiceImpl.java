package com.example.demo.servicesImpl.produit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.ProductUtils.ProductCSVHelper;
import com.example.demo.ProductUtils.ProductGeoLocationsPDFGenerator;
import com.example.demo.entity.DetailProduit;
import com.example.demo.entity.Fournisseur;
import com.example.demo.entity.ImagesProduit;
import com.example.demo.entity.MapLocalisationProduitFournisseur;
import com.example.demo.entity.Produit;
import com.example.demo.repository.produit.IDetailProduitRepository;
import com.example.demo.repository.produit.IFournisseurRepository;
import com.example.demo.repository.produit.IImagesProduitRepository;
import com.example.demo.repository.produit.IMapLocalisationProduitFournisseurRepository;
import com.example.demo.repository.produit.IProduitRepository;
import com.example.demo.services.produit.IProduitService;

import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	IProduitRepository 	produitRepository;
	@Autowired
	IDetailProduitRepository 	detailProduitRepository;
	@Autowired
	IFournisseurRepository 	fournisseurRepository;
	@Autowired
	IImagesProduitRepository imagesProduitRepository;
	@Autowired
	IMapLocalisationProduitFournisseurRepository maplocalisationRepository;
	 @Autowired
	 JavaMailSender emailSender;
	
	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits=produitRepository.findAll();
		/*for(Produit p:produits){
			log.info("Produit : "+p);
		}*/
		return produits;
	}

	@Override
	public Produit addProduit(Produit p,String idFournisseur) {
		log.info("ajout produit aves succes "+p);
		DetailProduit dp=p.getDetailProduit();
		DetailProduit sdp= detailProduitRepository.save(dp);
		p.setDetailProduit(sdp);
		Fournisseur f=fournisseurRepository.findById(idFournisseur).orElse(null) ;
		p.setFournisseur(f );
		//System.out.println("ffffffffffffffffffff");
		Set<ImagesProduit> imgp=p.getImagesProduit();
		//System.out.println(imgp);
		//p.setImagesProduit(null);
		for(ImagesProduit i:imgp){
			//System.out.println(i);
			String s=i.getUrl().replace("files", "filesM");
			i.setUrl(s);
			imagesProduitRepository.save(i);
			p.getImagesProduit().add(i);
		}
		MapLocalisationProduitFournisseur geomap=p.getMapLocalisation();
		MapLocalisationProduitFournisseur sgeomap=maplocalisationRepository.save(geomap);
		p.setMapLocalisation(sgeomap);
		System.out.println(p);
		return produitRepository.save(p);
	}

	@Override
	public Produit modProduit(Produit p) {
		log.info("modifier produit aves succes "+p);
		return produitRepository.save(p);
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);
	}

	@Override
	public Produit retrieveProduit(Long id) {
		return produitRepository.findById(id).orElse(null);

	}
	@Override
	public ByteArrayInputStream loadGeoMapCSV() {
	    List<MapLocalisationProduitFournisseur> geoLocationsProduits = maplocalisationRepository.findAll();

	    ByteArrayInputStream in = ProductCSVHelper.GeoLocationsProductsToCSV(geoLocationsProduits);
	    return in;
	  }
	
	@Override
	public ByteArrayInputStream loadGeoMapPDF() {
	    List<MapLocalisationProduitFournisseur> geoLocationsProduits = maplocalisationRepository.findAll();

	    ByteArrayInputStream bis = ProductGeoLocationsPDFGenerator.
	    		productsGeoMapLocationsPDFReport(geoLocationsProduits);

	    return bis;
	  }
	
	@Scheduled(cron = "*/60 * * * * *")
	public List<Produit> retrieveStatusStock() {
		List<Produit> produits=produitRepository.findproduitStatusStock();
		//String produitAlert = "";
		//List<Produit> produits = null;
		//log.info("Produit à restocker : "+produitAlert);
		//System.out.println("produitAlert :"+produits);
		for(Produit p:produits){
			   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			   System.out.println(dtf.format(now));  
			System.out.println("produitAlert : id : "+p.getIdProduit()+" libelle : "+p.getLibelle());
			System.out.println("fournisseur Alert :"+p.getFournisseur().getEmail());
			//send mail to fournisseur 
			this.sendSimpleEmail(p.getFournisseur().getEmail(), p.getIdProduit()+" libelle : "+p.getLibelle());
			
			
		}
		return produits;
	}
	  public void sendSimpleEmail(String to,String listproduits) {

	        // Create a Simple MailMessage.
	        SimpleMailMessage message = new SimpleMailMessage();
	        
	        message.setTo(to);
	        message.setSubject("stock produits epuisé ");
	        message.setText("Bonjour Mr "+to +" Produit stock epuisé : "+listproduits);

	        // Send Message!
	        this.emailSender.send(message);

	    }
	@Override
	public Produit assignFournisseurToProduit(String fournisseurId, Long produitId) {
		// TODO Auto-generated method stub
		Fournisseur f=fournisseurRepository.findById(fournisseurId).orElse(null);
		Produit p=produitRepository.findById(produitId).orElse(null);
		
		p.setFournisseur(f); 
		//System.out.println(p);
		produitRepository.flush();
		
		return produitRepository.findById(produitId).orElse(null); 
		//log.info("assignFournisseurToProduit done!");

	}

	@Override
	public List<Produit> getProductsCreatedBetween(Date dateDebut, Date dateFin) {
		List<Produit> produits=produitRepository.getProductsCreatedBetween(dateDebut,dateFin);

		return produits;
	}
	
	@Override
	public float getProductsRevenueBetween(Date dateDebut, Date dateFin) {
		float totalProduits=produitRepository.getProductsRevenueBetween(dateDebut,dateFin);
		
		return totalProduits;
	}

	@Override
	public List<Produit> getProductsLimiteConsommation(Date dateDebut, Date dateFin) {
		 //  LocalDate now = LocalDate.now();  
		   
	//	   Date todayDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

		 //  Date todayDate = now.get .format(dtf);
		 //  System.out.println(dtf.format(now)); 
		//  Date d=(Date) new java.util.Date();
		List<Produit> produits=produitRepository.getProductsLimiteConsommation(new java.util.Date() ,dateDebut,dateFin);

		return produits;
	}
	@Override
	public Map<String, Integer> statistiqueProduit(){
		Map<String, Integer> graphData = new TreeMap<>();
		List<String> ldp=produitRepository.stats();
		
		for (String str:ldp )
		{
			
			String[] res=	str.split(",",2);
	    	graphData.put(res[0], Integer.parseInt( res[1]) ) ;
	    	
		}
	    
        //System.out.println(produitRepository.stats());
        return graphData   ;
	}
	
}
