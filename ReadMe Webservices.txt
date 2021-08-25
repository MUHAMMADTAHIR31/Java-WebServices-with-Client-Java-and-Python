How to run simple WebService with axis (Apache eXtensible Interaction System)
------------------------------------------------------------------------------
step 1
======
you must download following zip file from given URLs

(1) apache-axis1.4-custom.zip (https://github.com/rbtteamhuawei/apache-axis1.4-custom)  
(2) mail.jar.zip and activation.jar.zip (http://www.java2s.com/Code/Jar/a/Downloadactivationjar.htm)
(3) tomcate5.0.zip (http://apache.mirrors.tds.net/tomcat/tomcat-5/v5.0.28/bin/jakarta-tomcat-5.0.28.exe)
(4) jdk-8u301-windows-x64.exe  (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)  

step 2
======
extract all above mentioned setups in c:\ drive

you will find following folders

(1) apache-axis1.4-custom (rename apache-axis1.4-custom with axis-1_4)
(2) mail.jar and activation.jar
(3) tomcate5.0
(4) jdk-8u301-windows-x64.exe (install it)

step 3
======
- copy [axis] folder from c:\axis-1_4\webapps and past in the C:\Tomcat 5.0\webapps
- copy activation.jar and mail.jar files from quickstart folder and past into C:\axis-1_4\lib
- copy [ tools.jar ] from  c:\Program Files\Java\jdk1.8.0_291\lib and paste into  C:\axis-1_4\lib

step 4
======
set classpath, java_home and path environment variables

(1) set classpath=.;C:\axis-1_4\lib\axis.jar;C:\axis-1_4\lib\commons-discovery-0.2.jar;C:\axis-1_4\lib\log4j-1.2.8.jar;C:\axis-1_4\lib\wsdl4j-1.5.1.jar;C:\axis-1_4\lib\axis-ant.jar;C:\axis-1_4\lib\commons-logging-1.0.4.jar;C:\axis-1_4\lib\axis-schema.jar;C:\axis-1_4\lib\jaxrpc.jar;C:\axis-1_4\lib\saaj.jar;C:\axis-1_4\lib\activation.jar;C:\axis-1_4\lib\tools.jar;C:\axis-1_4\lib\servlet.jar;C:\axis-1_4\lib\mail.jar;C:\axis-1_4\samples;C:\Tomcat 5.0\common\lib\servlet-api.jar

(2) set java_home=C:\Program Files\Java\jdk1.8.0_291;
Note:- (do not set JAVA_HOME path in this way C:\Program Files\Java\jdk1.8.0_291\bin) because happyaxis.jsp page (http://127.0.0.1:8080/axis/happyaxis.jsp); 
          under "Examining System Properties" you'll see that property java.home is set to the JRE path, not the JDK path in your machine.

(3) set path=C:\Program Files\Java\jdk1.8.0_291\bin;%path% (this path for java file compilation)
   


step 5
======
Create file with the name of HelloWorldService.java 
---------------------------------------------------

public class HelloWorldService
{
	public String HelloWorld(String data)
	{
		return "Hello World! You sent the string '" + data + "'.";
	}
}

rename HelloWorldService.java with *.jws extension such as HelloWorldService.jws

step 6
======
past HelloWorldService.jws file in C:\Tomcat 5.0\webapps\axis folder

step 7
======
test this example run tomcat application server by clicking following bat file
C:\Tomcat 5.0\bin\startup.bat

step 8
======
use webBrowser and enter following URL

http://127.0.0.1:8080/axis/HelloWorldService.jws

I received an HTML page saying that there was a service installed at that location.
Success! I went a step further by trying to call my HelloWorld method: 

step 9
======
http://127.0.0.1:8080/axis/HelloWorldService.jws?method=HelloWorld&data=Hi+my+name+is+Sachal

I received the XML from the method call.
if you receive following XML document that means call completed successfully! 

<?xml version="1.0" encoding="UTF-8" ?> 
 <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <soapenv:Body>
   <HelloWorldResponse soapenv:encodingStyle="http://schemas.xmlsoap.org/soap/encoding/">
   <HelloWorldReturn xsi:type="xsd:string">Hello World! You sent the string 'Hi my name is Sachal'.</HelloWorldReturn> 
   </HelloWorldResponse>
  </soapenv:Body>
 </soapenv:Envelope>

step 10
=======

Java Client 
-----------
-----------
-----------

Creating the web service turned out to be very simple.
My next task was to create a Java client that could call the service. 
The following is the source code for my Java client: 

c:\>edit HelloWorldClient.java

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class HelloWorldClient
{
	public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException
	{

		Service service = new Service();

		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(new URL("http://127.0.0.1:8080/axis/HelloWorldService.jws"));
		call.setOperationName(new QName("HelloWorld"));

		String returnValue = (String)call.invoke(new Object[]{"My name is Sachal"});

		System.out.println(returnValue);
	}
}



After compiling the source file,
I ran it and received data from the service.
The Java client was a success. 

c:\>set classpath=.;C:\axis-1_4\lib\axis.jar;C:\axis-1_4\lib\commons-discovery-0.2.jar;C:\axis-1_4\lib\log4j-1.2.8.jar;C:\axis-1_4\lib\wsdl4j-1.5.1.jar;C:\axis-1_4\lib\axis-ant.jar;C:\axis-1_4\lib\commons-logging-1.0.4.jar;C:\axis-1_4\lib\axis-schema.jar;C:\axis-1_4\lib\jaxrpc.jar;C:\axis-1_4\lib\saaj.jar;C:\axis-1_4\lib\activation.jar;C:\axis-1_4\lib\tools.jar;C:\axis-1_4\lib\servlet.jar;C:\axis-1_4\lib\mail.jar;C:\axis-1_4\samples;C:\Tomcat 5.0\common\lib\servlet-api.jar

c:\>javac HelloWorldClient.java
c:\>java HelloWorldClient

Hello World! You sent the string My name is Sachal.


Who says that the copy-paste antipattern is bad? :) 

After compiling the source file, I ran it and received data from the service.
The Java client was a success. 


Python Client 
-----------
-----------
-----------

Because SOAP is language independent,
I thought that I should be able to create a Python client to call my web service.
I did a quick Google search and found the main web site for Python Web Services.
I viewed the README for Zeep, and found an example similar to the following: (https://docs.python-zeep.org/en/master/)

import zeep

wsdl = 'http://127.0.0.1:8080/axis/HelloWorldService.jws?wsdl'
client = zeep.Client(wsdl=wsdl)
print(client.service.HelloWorld('Zeep', 'is cool'))


I ran it and received the correct message from the Axis service. 


Summary
-------
-------

I'm happy with the result of my experiments with Apache Axis.
I reached all of the goals that I set for myself in the beginning,
and I came to the conclusion that Axis is an excellent way to exchange
data between machines in a distributed environment.
 
Contributor : Abdul Hai and Mr. Sachal 
-------------------------------------------------
Co-contributor: Muhammad Tahir

Abdul Hai is a software Engineer in University of Sindh.
Sachal is software Architect in the University of Sindh, Jamshoro, Sindh, Pakistan.
Muhammad Tahir is Mr.Sachal's student at Bell Labs.

contact us find any problem
---------------------------
e-mail:SachalJoyo@yahoo.com
cell Phone:03003046820

e-mail:AbdulHaiMemon@hotmail.com
cell Phone:03013527695

e-mail:tahirsindhi872@gmail.com
cell Phone:03053523806



