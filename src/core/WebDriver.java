package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarLog;
import edu.umass.cs.benchlab.har.HarWarning;
import edu.umass.cs.benchlab.har.tools.HarFileReader;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

public class WebDriver {
	
	public long[] ars = {0,0,0,0};
	String pathstr = null;
	
	@SuppressWarnings({"static-access" })
	public void harGenerator(String url, String sNo, String path) throws NoSuchElementException {
		
		BrowserMobProxyServer server = new BrowserMobProxyServer();
		FirefoxDriver driver = null;
		
		try{
			server.start();
	
			/*			Get the Selenium Proxy Object			*/
			Proxy proxy = ClientUtil.createSeleniumProxy(server);
			
			/*			Associating Browser Capabilities			*/			
			DesiredCapabilities capabilities = new DesiredCapabilities().firefox();
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new FirefoxDriver(capabilities);
			
			/*			Capturing Performance Assets			*/
			server.newHar(url);
			driver.get(url);

			/*			Storing assets to HAR			*/
			try{
				Har har = server.getHar();
				
				pathstr = path+File.separator+sNo+".har";
				File file = new File(pathstr);
				file.getParentFile().mkdirs();
	    		file.createNewFile();
				
				FileOutputStream fos = new FileOutputStream(file);
				har.writeTo(fos);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			server.stop();
			driver.close();
		}
	}

	public long[] harReader() throws FileNotFoundException{
	 
		FileInputStream fis = new FileInputStream(pathstr);
	    HarFileReader r = new HarFileReader();
	    
	    try
	    {
	      List<HarWarning> warnings = new ArrayList<HarWarning>();
	      HarLog log = r.readHarFile(fis, warnings);
	      for (HarWarning w : warnings) {
	        System.out.println("File:" + fis+ " - Warning:" + w);
	      }
	      long htmlSize = 0;
	      long imageSize = 0;
	      long jsSize = 0;
	      long cssSize = 0;
	      
	      List<HarEntry> entries = log.getEntries().getEntries();
	      for (HarEntry entry : entries){
	    	  //System.out.println(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase());
	    	  
	       if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("text/html")){
	    	   htmlSize = htmlSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("image")){
	    	   imageSize = imageSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("javascript")){
	    	   jsSize = jsSize  + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
		   }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("text/css")){
	    	   cssSize = cssSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	       }
	     }
	      htmlSize = htmlSize/1024;
	      imageSize = imageSize/1024;
	      jsSize = jsSize/1024;
	      cssSize = cssSize/1024;
	      
	      ars[0] = htmlSize;
	      ars[1] = imageSize;
	      ars[2] = jsSize;
	      ars[3] = cssSize;
	      
	} catch (Exception e){
		e.printStackTrace();
	}
		     return ars;
	}
}
