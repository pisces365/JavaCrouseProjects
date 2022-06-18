package experiment;

public class TopicTwo {
	public static void main(String[] args)
	 {
	  int j=0;
	  for(int i=100;i<=200;++i)
	  {
	   if(i%3==0&&i%2!=0) 
	   {
	    ++j;
	    System.out.print(i+" ");
	    if(j%10==0) System.out.println("");
	   }
	  }
	 }
}
