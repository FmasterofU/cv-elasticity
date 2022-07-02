package rs.ac.uns.ftn.udd.fmaster.cvelasticity.indexing;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.indexing.handlers.DocumentHandler;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.indexing.handlers.PDFHandler;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.repository.UnitRepository;

@Service
public class Indexer {
	

	@Autowired
	private UnitRepository repository;
	
	public Indexer() {
	}
	
	
	public boolean delete(String filename){
		if(repository.equals(filename)){
			repository.delete(filename);
			return true;
		} else
			return false;
		
	}
	
	public boolean update(IndexUnit unit){
		unit = repository.save(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
	public boolean add(IndexUnit unit){
		unit = repository.index(unit);
		if(unit!=null)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param file Direktorijum u kojem se nalaze dokumenti koje treba indeksirati
	 */
	public int index(File file){		
		DocumentHandler handler = null;
		String fileName = null;
		int retVal = 0;
		try {
			File[] files;
			if(file.isDirectory()){
				files = file.listFiles();
			}else{
				files = new File[1];
				files[0] = file;
			}
			for(File newFile : files){
				if(newFile.isFile()){
					fileName = newFile.getName();
					handler = getHandler(fileName);
					if(handler == null){
						System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
						continue;
					}	
					if(add(handler.getIndexUnit(newFile)))
						retVal++;
				} else if (newFile.isDirectory()){
					retVal += index(newFile);
				}
			}
			System.out.println("indexing done");
		} catch (Exception e) {
			System.out.println("indexing NOT done");
		}
		return retVal;
	}
	

	public DocumentHandler getHandler(String fileName){
		if(fileName.endsWith(".pdf")){
			return new PDFHandler();
		}else{
			return null;
		}
	}

}