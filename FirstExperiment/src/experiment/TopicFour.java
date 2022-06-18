package experiment;
import java.util.Scanner;
public class TopicFour {
	public static void main(String[] args)
	 {
	  Scanner scn =new Scanner(System.in);
	  double wage=scn.nextDouble();
	  double tag=wage-3500;
	  double money=0;
	  if(tag<=1500)
	  {
	   money=tag*0.03;
	  }
	  else if(tag<=4500)
	  {
	   money=1500*0.03+(tag-1500)*0.1;
	  }
	  else if(tag<=9000)
	  {
	   money=1500*0.03+3000*0.1+(tag-4500)*0.2;
	  }
	  else if(tag<=35000)
	  {
	   money=1500*0.03+3000*0.1+4500*0.2+(tag-9000)*0.25;
	  }
	  else if(tag<=55000)
	  {
	   money=1500*0.03+3000*0.1+4500*0.2+26000*0.25+(tag-35000)*0.3;
	  }
	  else if(tag<=80000)
	  {
	   money=1500*0.03+3000*0.1+4500*0.2+26000*0.25+20000*0.3+(tag-55000)*0.35;
	  }
	  else money=1500*0.03+3000*0.1+4500*0.2+26000*0.25+20000*0.3+25000*0.35+(tag-80000)*0.45;
	 //123
	  System.out.println(money);
	 }
}
