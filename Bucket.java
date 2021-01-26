import java.io.*;
import java.util.*;
class Queue
{
int q[],f=0,r=0,size;
void insert(int n)
{
Scanner in = new Scanner(System.in);
q=new int[10];
for(int i=0;i&lt;n;i++)
{
System.out.print(&quot;\nEnter &quot; + i + &quot; element: &quot;);
int ele=in.nextInt();
if(r+1&gt;10)
{
System.out.println(&quot;\nQueue is full \nLost Packet: &quot;+ele); break;
}
else
{
r++;
q[i]=ele;
}
}
}
void delete()
{
Scanner in = new Scanner(System.in);
Thread t=new Thread();
if(r==0)
System.out.print(&quot;\nQueue empty &quot;);

else
{
for(int i=f;i&lt;r;i++)
{
try
{
t.sleep(1000);
}
catch(Exception e){}
System.out.print(&quot;\nLeaked Packet: &quot;+q[i]);
f++;
}
}
System.out.println();

}

}
class Leaky extends Thread
{
public static void main(String ar[]) throws Exception {
Queue q=new Queue();
Scanner src=new Scanner(System.in);
System.out.println(&quot;\nEnter the packets to be sent:&quot;);
int size=src.nextInt();
p. insert(size);
q.delete();
}

}
