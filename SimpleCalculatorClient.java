import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import java.util.*;

public class SimpleCalculatorClient{
	
	public static void main(String[] args) throws ServiceException, MalformedURLException, RemoteException{

		Service service = new Service();

		Call call = (Call)service.createCall();
		call.setTargetEndpointAddress(new URL("http://127.0.0.1:8080/axis/SimpleCalculator.jws"));
		
		System.out.println("------------Simple Calculator-----------------");
		System.out.println("1.Addition \n2.Subtraction \n3.Multilpe \n4.Divided \n5.Exit");
		System.out.println("-----------------------------");
		System.out.println("Enter Operation Number (like 1 or 2)");
		
		Scanner ob = new Scanner(System.in);
		int op = ob.nextInt(); 
		
		System.out.println("Enter the 1st Number ");
		int num1 = ob.nextInt();
		
		System.out.println("Enter the 2nd Number ");
		int num2 = ob.nextInt();
		
		int returnValue;
		
		
		switch(op){
			
			case 1:
				call.setOperationName(new QName("add"));			
				returnValue = (int)call.invoke(new Object[]{num1,num2});
				System.out.println("Answer: "+returnValue);
			break;
			
			case 2:
				call.setOperationName(new QName("sub"));
				returnValue = (int)call.invoke(new Object[]{num1,num2});
				System.out.println("Answer: "+returnValue);
			break;
			
			case 3:
				call.setOperationName(new QName("multiply"));
				returnValue = (int)call.invoke(new Object[]{num1,num2});
				System.out.println("Answer: "+returnValue);
			break;
			
			case 4:
				call.setOperationName(new QName("divid"));
				returnValue = (int)call.invoke(new Object[]{num1,num2});
				System.out.println("Answer: "+returnValue);
			break;
			
			case 5:
				System.exit(0);
			break;
			
			default:
				System.out.println("Please enter correct operation number");
			break;
		}
	}
}