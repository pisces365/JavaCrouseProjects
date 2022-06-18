package experiment;
import java.util.Scanner;
public class TopicOne {
	  public static void main(String[] args)
	  {
	   Scanner scn =new Scanner(System.in);
	   double t=scn.nextDouble(),y=0;
	   if(t>=0&&t<1)
	   {
	    y=t-1;
	   }
	   else if(t>=1&&t<3)
	   {
	    y=Math.pow(t, 3)-2*t-3;
	   }
	   else if(t>=3&&t<5)
	   {
	    y=(Math.pow(t, 2))*Math.cos(t);
	   }
	   else y=t+1;
	  
	   System.out.println(String.format("%.2f", y));
	  }
}
