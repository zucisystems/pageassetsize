# pageassetsize

Introduction:

  	Page Asset size helps to troubleshoot the performance issues in a web-page.
	It gathers size of the content of Web pages using Selenium Web Driver and results are stored and dispalyed in XLS.

Input:

	Webpage URL whose performance has to be analysed is entered in spreasheet and it is used as input to this process.

Tools Used:

	Selenium Web Driver (v2.53.0)
	BrowserMob Proxy (v2.1.2)
	harlib (v1.1.2)
	harreader(v2.0.1)
	Apache POI (v3.14)

Output:

	HAR Size is acrnonym for HTTP Archive. HAR allows us to get the size of HTML, Image, Java-Script, CSS, Media and Font. 
	The result is displayed in Spreadsheet.

Tools in Detail:

	Selenium Web Driver:
	Selenium-WebDriver is a tool to support dynamic web pages where elements of a page may change without the page itself being reloaded.

	BrowserMob Proxy:
	It can capture performance data for web apps (via the HAR format), 
	manipulate browser behavior and traffic, 
	such as whitelisting and blacklisting content, 
	simulating network traffic and latency, 
	and rewriting HTTP requests and responses.

	HarLib:
	HarLib is an open source Java library for the HTTP Archive Specification (HAR)
	Read/write HAR files from Java
	HarLib relies on Jackson for the parsing. 

	Har Reader:
	It is a Library for accessing HTTP Archives (HAR) with Java

	Apache POI: 
	Apache POI is an API that allows programmers to create, modify, and display MS Office (Spreadsheet) files using Java programs. 
	It is an open source library developed and distributed by Apache Software Foundation to design or modify Microsoft Office files using Java program. 
	It contains classes and methods to decode the user input data or a file into MS Office (Spreadsheet) documents.

Installation Details:

	Include the library in your pom.xml, if you are using Maven
	
        <modelVersion>4.0.0</modelVersion>
        <groupId>pageassetsize</groupId>
        <artifactId>pageassetsize</artifactId>
        <version>0.0.1-SNAPSHOT</version>

Conclusion:

	This uses open source components and cost effective tool for analysing performance issues.
