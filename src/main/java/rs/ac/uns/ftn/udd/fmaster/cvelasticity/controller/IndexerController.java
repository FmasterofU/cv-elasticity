package rs.ac.uns.ftn.udd.fmaster.cvelasticity.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.UploadModel;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.indexing.Indexer;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.Education;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.IndexUnitService;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.RequestService;

@RestController
public class IndexerController {

		private static String DATA_DIR_PATH;
	
		static {
			ResourceBundle rb=ResourceBundle.getBundle("application");
			DATA_DIR_PATH=rb.getString("dataDir");
		}
		
		@Autowired
		private Indexer indexer;
		@Autowired
		private RequestService requestService;
		@Autowired
		private IndexUnitService indexUnitService;
		
/*		
		@GetMapping("/reindex")
		public ResponseEntity<String> index() throws IOException {
			File dataDir = getResourceFilePath(DATA_DIR_PATH);
			long start = new Date().getTime();
			int numIndexed = indexer.index(dataDir);
			long end = new Date().getTime();
			String text = "Indexing " + numIndexed + " files took "
					+ (end - start) + " milliseconds";
			return new ResponseEntity<String>(text, HttpStatus.OK);
		}
*/		
		private File getResourceFilePath(String path) {
		    URL url = this.getClass().getClassLoader().getResource(path);
		    File file = null;
		    try {
		        file = new File(url.toURI());
		    } catch (URISyntaxException e) {
		        file = new File(url.getPath());
		    }   
		    return file;
		}
		


	    @PostMapping("/index/add")
	    public ResponseEntity<String> multiUploadFileModel(@ModelAttribute UploadModel model, HttpServletRequest request) {
	    	
	    	model.setIp(requestService.getClientIp(request));

	        try {

	        	indexUploadedFile(model);

	        } catch (IOException e) {
	            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);

	    }
		    
		    
	    //save file
	    private String saveUploadedFile(MultipartFile file) throws IOException {
	    	String retVal = null;
            if (! file.isEmpty()) {
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(getResourceFilePath(DATA_DIR_PATH).getAbsolutePath() + File.separator + file.getOriginalFilename());
	            Files.write(path, bytes);
	            retVal = path.toString();
            }
            return retVal;
	    }
	    
	    private void indexUploadedFile(UploadModel model) throws IOException{
	    	
	    	for (MultipartFile file : model.getFiles()) {

	            if (file.isEmpty()) {
	                continue; //next please
	            }
	            String fileName = saveUploadedFile(file);
	            if(fileName != null){
	            	IndexUnit indexUnit = indexer.getHandler(fileName).getIndexUnit(new File(fileName));
	            	indexUnit.setName(model.getName());
	            	indexUnit.setSurname(model.getSurname());
	            	indexUnit.setEmail(model.getEmail());
	            	Education education = new Education();
	            	education.setEducationgrade(model.getEducationgrade());
	            	education.setEducationlevel(model.getEducationlevel());
	            	indexUnit.setEducation(education);
	            	indexUnit.setAddress(model.getAddress());
	            	indexUnit.setCity(model.getCity());
	            	indexUnit.setZipcode(model.getZipcode());
	            	indexUnit.setIp(model.getIp());
	            	indexUnit = indexUnitService.recalculateIndexUnitFields(indexUnit);
	            	indexer.add(indexUnit);
	            	break;
	            }
	    	}
	    }

	
}
