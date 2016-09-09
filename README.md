## Page Asset Size

## Introduction:

  	Page Asset size program helps in calculating the total size of assets in a web page categorized by Image, HTML, Javascript, CSS, Media, Font, XHR etc. 
  	
  	Can't this be done by a Firebug or a developer tool? Yes, it can be done, just imagine you are a company playing with thousands of dynamic URLs, how would you do it with ease?
  	
  	Another example of a e-commerce retail engineering team wants to look for page assets based on variety of keywords, how it can be done in an automated fashion?

## Input:

	Webpage URL whose performance has to be analyzed is entered in spreadsheet and it is used as input to this process.
	Device type (for example desktop or mobile/tablet)
	User Agent (for example iPhone, iPad, Android Mobile, Android Tablet).

## Tools Used:

	1. Selenium Web Driver (v2.53.0)
	2. BrowserMob Proxy (v2.1.2)
	3. HarLib (v1.1.3)
	4. HarReader(v2.0.1)
	5. Apache POI (v3.14)
	
## Output:

	1. HAR (HTTP Archive) File. HAR captures the finer details of various page assets like HTML, Image, Java-Script, CSS, Media and Font.
	2. The categorized assets sizes which is tested with different device types and different user agent are stored into an excel.
	3. Time taken per asset group on different user agents are stored into an excel.
The above are abailable under ../src/testresults/<date_time> of execution.

## Quick Overview of Maven Dependencies

* **Selenium Web Driver:**
	Selenium-WebDriver helps in launching the Web Page and wait until its completely rendered
        <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>2.53.0</version>
        </dependency>

* **BrowserMob Proxy:**
	Browser-Mob Proxy captures the network traffic as an when the Page is loaded using Web Driver.
	<dependency>
	<groupId>net.lightbody.bmp</groupId>
	<artifactId>browsermob-core</artifactId>
	<version>2.1.2</version>
	</dependency>

* **HarLib and Jackson:**
	HarLib is an open source Java library for to Read/write HAR files in Java.
	HarLib relies on Jackson for the parsing. 
	<dependency>
	<groupId>edu.umass.cs.benchlab</groupId>
	<artifactId>harlib</artifactId>
	<version>1.1.3</version>
	</dependency> 

* **HarReader:**
	It is a Library for accessing HTTP Archives (HAR) with Java.
	<dependency>
    	<groupId>de.sstoehr</groupId>
    	<artifactId>har-reader</artifactId>
    	<version>2.0.1</version>
	</dependency>

* **Apache POI:**
	Apache POI is an API that allows programmers to create, modify, and display MS Office (Spreadsheet) files using Java programs.
	It is an open source library developed and distributed by Apache Software Foundation to design or modify Microsoft Office files using Java program. 
	It contains classes and methods to decode the user input data or a file into MS Office (Spreadsheet) documents.
	
	<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi</artifactId>
	<version>3.14</version>
	</dependency>
	
	<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi-ooxml</artifactId>
	<version>3.14</version>
	</dependency>

## Installation Details:

	Include the library in your pom.xml, if you are using Maven
	
        <modelVersion>4.0.0</modelVersion>
        <groupId>pageassetsize</groupId>
        <artifactId>pageassetsize</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        
## How to Use it:

* The user's input (URL's), Device Type (for example desktop or mobile/tablet), User Agent (for example iPhone, iPad, Android Mobile, Android Tablet) are to be given in Spreadsheet, present in the location,
	
	Location : \src\data\ input-file (Core.xls)
	
	![Input-Snap](https://github.com/zucisystems/pageassetsize/blob/master/images/Input-Snap.PNG)
	
* Run the application by right-click on file, as follows,
	
	file name (Core.java) > Run As > Java Application
	
	![Run Application](https://github.com/zucisystems/pageassetsize/blob/master/images/Run%20Application.jpg)
	
* The test results are stored in the location in Spreadsheet,
	1. Result showing Size of each Asset group tested in different User Agent.
	2. Result showing Time taken per Asset Group.
	
	Location : \src\testresults\ dynamic-folder (Page Assets.xls)	

	![Result-Snap](https://github.com/zucisystems/pageassetsize/blob/master/images/Result-Snap-1.PNG)
		
	![Result-Snap](https://github.com/zucisystems/pageassetsize/blob/master/images/Result-Snap-2.PNG)
	
## Conclusion:

	This uses open source components and cost effective tool for analysing performance issues.
