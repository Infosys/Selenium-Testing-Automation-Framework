package test;
/**
 * The test class is used to test the Report class
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.util.StreamReaderDelegate;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class test {
	public static void report(){
		
		
		try {
			
			System.setProperty("javax.xml.transform.TransformerFactory",
                    "net.sf.saxon.TransformerFactoryImpl");
			
			TransformerFactory transFact= TransformerFactory.newInstance(); 
			Transformer trans=transFact.newTransformer(new StreamSource("xml/testng-results.xsl") );
			
			trans.setParameter("testNgXslt.outputDir", "D:/newEclipseWorksspace/SeleniumTemp/test-output");
			
			trans.setParameter("testNgXslt.cssFile", "D:/newEclipseWorksspace/SeleniumTemp/xml/custom.css");
			trans.setParameter("testNgXslt.showRuntimeTotals", "true");
			trans.setParameter("testNgXslt.sortTestCaseLinks", "true");
			trans.setParameter("testNgXslt.testDetailsFilter", "FAIL,PASS,SKIP,CONF,BY_CLASS");
			
			FileOutputStream os=new FileOutputStream("test-output/report.html");
			trans.transform(new StreamSource("xml/testng-results.xml"), new StreamResult(os));
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
