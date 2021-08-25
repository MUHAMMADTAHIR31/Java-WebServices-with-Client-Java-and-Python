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