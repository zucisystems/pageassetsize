package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import edu.umass.cs.benchlab.har.HarEntry;
import edu.umass.cs.benchlab.har.HarLog;
import edu.umass.cs.benchlab.har.HarWarning;
import edu.umass.cs.benchlab.har.tools.HarFileReader;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

public class WebDriver {
	public long[] ars = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	String pathstr = null;
	FileInputStream fis = null;
	
	@SuppressWarnings({"static-access" })
	public void harGenerator(String url, String sNo, String useragent, String path) throws NoSuchElementException, Exception {
		
		BrowserMobProxyServer server = new BrowserMobProxyServer();
		DesiredCapabilities capabilities = null;
		RemoteWebDriver driver = null;
		Properties prop = new Properties();
		InputStream is = null;
		
		try{
			server.start();
	
			/*			Get the Selenium Proxy Object			*/
			Proxy proxy = ClientUtil.createSeleniumProxy(server);
			
			is = new FileInputStream("useragent.properties");
			prop.load(is);
			
			if(useragent.isEmpty()){
				useragent = prop.getProperty("default");
			}
			
			/*			Associating Browser Capabilities			*/
			if(useragent.contentEquals("firefox")){			
			capabilities = new DesiredCapabilities().firefox();
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new FirefoxDriver(capabilities); 
			} else if(useragent.contentEquals("chrome")){			
			capabilities = new DesiredCapabilities().chrome();
			ChromeOptions options = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver","D:\\Softwares Setup Files\\Softwares\\New folder\\chromedriver.exe");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new ChromeDriver(capabilities); 
			}else if(useragent.contentEquals("ie")){			
			capabilities = new DesiredCapabilities().internetExplorer();
			System.setProperty("webdriver.ie.driver","D:\\Softwares Setup Files\\Softwares\\New folder\\IEDriverServer.exe" );
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			//capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new InternetExplorerDriver(capabilities); 
			} else if(useragent.contentEquals("android")){			
			capabilities = new DesiredCapabilities().android();
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new FirefoxDriver(capabilities); 
			} else if(useragent.contentEquals("iphone")){			
			capabilities = new DesiredCapabilities().iphone();
			//capabilities.setCapability(CapabilityType.BROWSER_NAME, "iphone");
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new FirefoxDriver(capabilities); 
			} else if(useragent.contentEquals("ipad")){			
			capabilities = new DesiredCapabilities().ipad();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
			driver = new FirefoxDriver(capabilities); 
			}
			
			/*			Capturing Performance Assets	*/		
			server.newHar(url);
			driver.manage().timeouts().implicitlyWait(60000, TimeUnit.SECONDS);
			driver.get(url);
			
			/*			Storing assets to HAR			*/
			try{
				//Thread.sleep(20000);
				driver.manage().timeouts().implicitlyWait(60000, TimeUnit.SECONDS);
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
	      long htmlSize = 0;
	      long cssSize = 0;
	      long jsSize = 0;
	      long xhrSize = 0;
	      long imageSize = 0;
	      long mediaSize = 0;
	      long fontSize = 0;
	      long otherSize = 0;
	    
	      long htmlTime = 0;
	      long cssTime = 0;
	      long jsTime = 0;
	      long xhrTime = 0;
	      long imageTime = 0;
	      long mediaTime = 0;
	      long fontTime = 0;
	      long otherTime = 0;
	    try
	    {
	          List<HarWarning> warnings = new ArrayList<HarWarning>();
	          HarLog log = r.readHarFile(fis, warnings);
	        //HarLog log = r.readHarFile(fis);
	       // for (HarWarning w : warnings) {
	        // System.out.println("File:" + fis+ " - Warning:" + w);
	      
	      List<HarEntry> entries = log.getEntries().getEntries();
	      for (HarEntry entry : entries){
	    	  System.out.println(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase());
	    	  
	       if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("text/html")){
	    	   htmlSize = htmlSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   htmlTime = htmlTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("text/css")){
	    	   cssSize = cssSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   cssTime = cssTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("javascript")){
	    	   jsSize = jsSize  + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   jsTime = jsTime + entry.getTime();
		   }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("xhr")){
	    	   xhrSize = xhrSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   xhrTime = xhrTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("image")){
	    	   imageSize = imageSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   imageTime = imageTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("video")){
	    	   mediaSize = mediaSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   mediaTime = mediaTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("font")){
	    	   fontSize = fontSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   fontTime = fontTime + entry.getTime();
	       }else if(entry.getResponse().getContent().getMimeType().trim().toString().toLowerCase().contains("application")){
	    	   otherSize = otherSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   otherTime = otherTime + entry.getTime();
	       }else{
	    	   otherSize = otherSize + entry.getResponse().getBodySize() + entry.getResponse().getHeadersSize();
	    	   otherTime = otherTime + entry.getTime();
	       }
	      }  
	     // } 
	      htmlSize = htmlSize/1024;
	      cssSize = cssSize/1024;
	      jsSize = jsSize/1024;
	      xhrSize = xhrSize/1024;
	      imageSize = imageSize/1024;
	      mediaSize = mediaSize/1024;
	      fontSize = fontSize/1024;
	      otherSize = otherSize/1024;
	      
	      ars[0] = htmlSize;
	      ars[1] = cssSize;
	      ars[2] = jsSize;
	      ars[3] = xhrSize;
	      ars[4] = imageSize;
	      ars[5] = mediaSize;
	      ars[6] = fontSize;
	      ars[7] = otherSize;
	      
	      htmlTime = htmlTime/1000;
	      cssTime = cssTime/1000;
	      jsTime = jsTime/1000;
	      xhrTime = xhrTime/1000;
	      imageTime = imageTime/1000;
	      mediaTime = mediaTime/1000;
	      fontTime = fontTime/1000;
	      otherTime = otherTime/1000;
	      
	      ars[8] = htmlTime;
	      ars[9] = cssTime;
	      ars[10] = jsTime;
	      ars[11] = xhrTime;
	      ars[12] = imageTime;
	      ars[13] = mediaTime;
	      ars[14] = fontTime;
	      ars[15] = otherTime;
	      
	} catch (Exception e){
		e.printStackTrace();
	}        
	return ars;
	}
}
