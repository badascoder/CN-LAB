import java.io.;
class Crc
{
public static void main(String args[]) throws IOException {
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
int[ ] data;
int[ ]div;
int[ ]divisor;
int[ ]rem;
int[ ] crc;
int data_bits, divisor_bits, tot_length;
System.out.println(&quot;Enter number of data bits : &quot;);
data_bits=Integer.parseInt(br.readLine());
data=new int[data_bits];
System.out.println(&quot;Enter data bits : &quot;);
for(int i=0; i&lt;data_bits; i++)
data[i]=Integer.parseInt(br.readLine()); System.out.println(&quot;Enter number of bits in divisor : &quot;);
divisor_bits=Integer.parseInt(br.readLine()); divisor=new int[divisor_bits];
System.out.println(&quot;Enter Divisor bits : &quot;);
for(int i=0; i&lt;divisor_bits; i++)
divisor[i]=Integer.parseInt(br.readLine());
/* System.out.print(&quot;Data bits are : &quot;);
for(int i=0; i&lt; data_bits; i++)

System.out.print(data[i]);
System.out.println();
System.out.print(&quot;divisor bits are : &quot;);
for(int i=0; i&lt; divisor_bits; i++)
System.out.print(divisor[i]);
System.out.println();
*/ tot_length=data_bits+divisor_bits-1;
div=new int[tot_length];
rem=new int[tot_length];
crc=new int[tot_length];

/*------------------ CRC GENERATION-----------------------*/
for(int i=0;i&lt;data.length;i++)
div[i]=data[i];
System.out.print(&quot;Dividend (after appending 0&#39;s) are : &quot;); for(int i=0; i&lt; div.length; i++)
System.out.print(div[i]);
System.out.println();
for(int j=0; j&lt;div.length; j++){
rem[j] = div[j];
}
rem=divide(div, divisor, rem);
for(int i=0;i&lt;div.length;i++) //append dividend and ramainder
{
crc[i]=(div[i]^rem[i]);
}
System.out.println();
System.out.println(&quot;CRC code : &quot;);
for(int i=0;i&lt;crc.length;i++)
System.out.print(crc[i]);

/*-------------------ERROR DETECTION---------------------*/
System.out.println();
System.out.println(&quot;Enter CRC code of &quot;+tot_length+&quot; bits : &quot;);
for(int i=0; i&lt;crc.length; i++)
crc[i]=Integer.parseInt(br.readLine());
/* System.out.print(&quot;crc bits are : &quot;);
for(int i=0; i&lt; crc.length; i++)
System.out.print(crc[i]);
System.out.println();
*/
for(int j=0; j&lt;crc.length; j++){

rem[j] = crc[j];
}
rem=divide(crc, divisor, rem);

for(int i=0; i&lt; rem.length; i++)
{
if(rem[i]!=0)
{
System.out.println(&quot;Error&quot;);
break;
}
if(i==rem.length-1)
System.out.println(&quot;No Error&quot;);
}
System.out.println(&quot;THANK YOU.... :)&quot;);
}
static int[] divide(int div[],int divisor[], int rem[])
{
int cur=0;
while(true)
{
for(int i=0;i&lt;divisor.length;i++)
rem[cur+i]=(rem[cur+i]^divisor[i]);
while(rem[cur]==0 &amp;&amp; cur!=rem.length-1)
cur++;
if((rem.length-cur)&lt;divisor.length)
break;
}
return rem;
}
}
