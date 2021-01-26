TCP Client
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
class Client
{
public static void main(String args[])throws Exception
{
String address = &quot;&quot;;
Scanner sc=new Scanner(System.in);
System.out.println(&quot;Enter Server Address: &quot;);
address=sc.nextLine();
//create the socket on port 5000
Socket s=new Socket(address,5000);
DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println(&quot;Send Get to start...&quot;);
String str=&quot;&quot;,filename=&quot;&quot;;
try
{
while(!str.equals(&quot;start&quot;))
str=br.readLine();
dout.writeUTF(str);
dout.flush();
filename=din.readUTF();
System.out.println(&quot;Receving file: &quot;+filename);
filename=&quot;client&quot;+filename;

System.out.println(&quot;Saving as file: &quot;+filename);
long sz=Long.parseLong(din.readUTF());
System.out.println (&quot;File Size: &quot;+(sz/(1024*1024))+&quot; MB&quot;);
byte b[]=new byte [1024];
System.out.println(&quot;Receving file..&quot;);
FileOutputStream fos=new FileOutputStream(new File(filename),true);
long bytesRead;
do
{
bytesRead = din.read(b, 0, b.length);
fos.write(b,0,b.length);
}while(!(bytesRead&lt;1024));
System.out.println(&quot;Comleted&quot;);
fos.close();
dout.close();
s.close();
}
catch(EOFException e)
{
//do nothing
}

}
}
TCP Server
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
class Server
{
public static void main(String args[])throws Exception {
String filename;
System.out.println(&quot;Enter File Name: &quot;);
Scanner sc=new Scanner(System.in);
filename=sc.nextLine();
sc.close();
while(true)
{

//create server socket on port 5000
ServerSocket ss=new ServerSocket(5000);
System.out.println (&quot;Waiting for request&quot;);
Socket s=ss.accept();
System.out.println (&quot;Connected With &quot;+s.getInetAddress().toString());

DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
try
{
String str=&quot;&quot;;
str=din.readUTF();
System.out.println(&quot;SendGet. Ok&quot;);
if(!str.equals(&quot;stop&quot;)){
System.out.println(&quot;Sending File: &quot;+filename);
dout.writeUTF(filename);
dout.flush();
File f=new File(filename);
FileInputStream fin=new FileInputStream(f);
long sz=(int) f.length();
byte b[]=new byte [1024];
int read;
dout.writeUTF(Long.toString(sz));
dout.flush();
System.out.println (&quot;Size: &quot;+sz);
System.out.println (&quot;Buf size: &quot;+ss.getReceiveBufferSize());
while((read = fin.read(b)) != -1) {
dout.write(b, 0, read);
dout.flush();
}
fin.close();
System.out.println(&quot;..ok&quot;);
dout.flush();
}
dout.writeUTF(&quot;stop&quot;);
System.out.println(&quot;Send Complete&quot;);
dout.flush();
}
catch(Exception e)
{
e.printStackTrace();
System.out.println(&quot;An error occured&quot;);
}
din.close();
s.close();
ss.close();
}
}
}
