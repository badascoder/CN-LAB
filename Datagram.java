UDP Client
import java.io.*;
import java.net.*;
public class UDPC
{
public static void main(String[] args)
{
DatagramSocket skt;
try
{
skt=new DatagramSocket();
String msg= &quot;text message &quot;;
byte[] b = msg.getBytes();
InetAddress host=InetAddress.getByName(&quot;127.0.0.1&quot;); int
serverSocket=6788;

DatagramPacket request =new DatagramPacket (b,b.length,host,serverSocket);

skt.send(request);
byte[] buffer =new byte[1000];

DatagramPacket reply= new DatagramPacket(buffer,buffer.length);
skt.receive(reply);
System.out.println(&quot;client received:&quot; +new String(reply.getData()));
skt.close();
}
catch(Exception ex)
{
}
}
}

UDP Server
import java.io.*;
import java.net.*;
public class UDPS
{
public static void main(String[] args)
{
DatagramSocket skt=null;
try
{
skt=new DatagramSocket(6788);
byte[] buffer = new byte[1000];
while(true)
{
DatagramPacket request = new DatagramPacket(buffer,buffer.length);
skt.receive(request);
String[] message = (new String(request.getData())).split(&quot; &quot;); byte[]
sendMsg= (message[1]+ &quot; server processed&quot;).getBytes();
DatagramPacket reply = new
DatagramPacket(sendMsg,sendMsg.length,request.getAddress
(),request.getPort());
skt.send(reply);
}
}
catch(Exception ex)
{
}
}
}
