package rs.ac.uns.ftn.udd.fmaster.cvelasticity.indexing.handlers;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.text.PDFTextStripper;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

public class PDFHandler extends DocumentHandler {

	@Override
	public IndexUnit getIndexUnit(File file) {
		IndexUnit retVal = new IndexUnit();
		try {
			String text = getText(file);
			retVal.setCvtext(text);
			retVal.setFilename(file.getCanonicalPath());
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return retVal;
	}
	
	
	public PDFParser getPDFParser(File file) throws IOException {
		PDFParser parser = new PDFParser(new RandomAccessFile(file, "r"));
		parser.parse();
		return parser;
	}

	@Override
	public String getText(File file) {
		try {
			PDFParser parser = getPDFParser(file);
			return getText(parser);
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(PDFParser parser) throws IOException {
		PDFTextStripper textStripper = new PDFTextStripper();
		String text = textStripper.getText(parser.getPDDocument());
		return text;
	}

}
