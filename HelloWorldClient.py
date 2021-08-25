import zeep

wsdl = 'http://127.0.0.1:8080/axis/HelloWorldService.jws?wsdl'
client = zeep.Client(wsdl=wsdl)

value = input("Enter your String : ")
print(client.service.HelloWorld(value))
