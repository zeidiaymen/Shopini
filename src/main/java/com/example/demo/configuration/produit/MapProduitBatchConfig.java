package com.example.demo.configuration.produit;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.entity.MapLocalisationProduitFournisseur;
import com.example.demo.produitBatch.produitGeoProcessor;
import com.example.demo.produitBatch.produitGeoWriter;



@Configuration
/*3. Activer le traitement par lot */
/*toDo5*/
@EnableBatchProcessing
@EnableScheduling
public class MapProduitBatchConfig {

	/*4. Création des variables de notre batch (nom fichier,
	 * nom job, nom step, nom lecteur*/
	private static final String FILE_NAME = "GeoMapLocations.csv";
	private static final String JOB_NAME = "listProduitsGeoMapJob";
	private static final String STEP_NAME = "processingStep";
	private static final String READER_NAME = "produitGeoItemReader";

	/*5. Lister les champs que nous souhaitons parser dans le
	 * fichier excel*/
	private String names = "lat,lng";
	private String delimiter = ",";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	
	

	/*7 Créer le bean associé au job et le lancer*/
	@Bean
	public Job listStocksJob(Step step1) {
		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
	}

	/*8 Créer le step associé au job et le lancer*/
	@Bean
	public Step stockStep() {
		return stepBuilderFactory.get(STEP_NAME).<MapLocalisationProduitFournisseur, MapLocalisationProduitFournisseur>chunk(2).reader(produitGeoItemReader())
				.processor(produitGeoItemProcessor()).writer(produitGeoItemWriter()).build();
	}
	
	/*9. étape 1 (ItemReader) Créer le reader pour récupérer les données depuis
	 * le fichier csv*/

	@Bean
	public ItemReader<MapLocalisationProduitFournisseur> produitGeoItemReader() {
		FlatFileItemReader<MapLocalisationProduitFournisseur> reader = new FlatFileItemReader<>();
		//reader.setResource(new ClassPathResource(FILE_NAME));
		reader.setResource(new ClassPathResource(FILE_NAME));
		reader.setName(READER_NAME);
		reader.setLinesToSkip(1);
		/*7. récupérer les données ligne par ligne du fichier excel */
		reader.setLineMapper(produitGeoLineMapper());
		return reader;

	}

	
	/*10. récupérer les données ligne par ligne du fichier excel */

	@Bean
	public LineMapper<MapLocalisationProduitFournisseur> produitGeoLineMapper() {

		final DefaultLineMapper<MapLocalisationProduitFournisseur> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(delimiter);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(names.split(delimiter));
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSet -> {
			MapLocalisationProduitFournisseur data = new MapLocalisationProduitFournisseur();
			//data.setIdGeoLocalisation((long) fieldSet.readInt(0) );
			data.setLat(fieldSet.readFloat(0) );
			data.setLng(fieldSet.readFloat(1) );
			return data;
		});
		return defaultLineMapper;
	}

	/* 11. étape 2 (ItemProcessor) fait appel à la classe StockProcessor
	 * qui se charge de modifier la logique des données selon
	 * nos besoins métiers */
	@Bean
	public ItemProcessor<MapLocalisationProduitFournisseur, MapLocalisationProduitFournisseur> produitGeoItemProcessor() {
		return new produitGeoProcessor();
	}

	
	/* 12. étape 3 (ItemWriter) fait appel à la classe StockWriter
	 * qui se charge de lancer le service de sauvegarde des 
	 * données liées à la partie stock dans la BD*/
	@Bean
	public ItemWriter<MapLocalisationProduitFournisseur> produitGeoItemWriter() {
		return new produitGeoWriter();
	}
	
}
