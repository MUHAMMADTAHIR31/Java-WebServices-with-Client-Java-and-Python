import zeep

wsdl = 'http://127.0.0.1:8080/axis/SimpleCalculator.jws?wsdl'
client = zeep.Client(wsdl=wsdl)

print("-------------Simple Calculator----------------------")
print("1.Addition \n2.Subtraction \n3.Multilpe \n4.Divided \n5.Exit")

value = input("Enter your operation : ")
num1  = input("Enter your 1st Number : ")
num2  = input("Enter your 2nd Number : ")

if value== '1' :
  print("Answer:",client.service.add(num1,num2))
elif value == '2':
  print("Answer:",client.service.sub(num1,num2))
elif value == '3':
  print("Answer:",client.service.multiply(num1,num2))
elif value == '4':
  print("Answer:",client.service.divid(num1,num2)) 
