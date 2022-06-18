package experiment;
import java.util.Scanner;
public class TopicOne2 {
	public static void main(String[] args)
	 {
	  Scanner scn =new Scanner(System.in);
	  double t=scn.nextInt(),y=0;
	  switch((int)t)
	  {
	  case 0:y=t-1;break;
	  case 1:y=Math.pow(t, 3)-2*t-3;break;
	  case 2:y=Math.pow(t, 3)-2*t-3;break;
	  case 3:y=(Math.pow(t, 2))*Math.cos(t);break;
	  case 4:y=(Math.pow(t, 2))*Math.cos(t);break;
	  default:y=t+1;break;
	  }
	 
	  System.out.println(String.format("%.2f", y));
	 }
}
