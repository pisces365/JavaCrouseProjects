package experiment;

public class TopicThree {
	public static void main(String[] args)
	 {
	  double e=0;
	  double change=1;
	  for(int i=1;i<=100;++i)
	  {
	   double var=change*((double)1/(double)i);
	   change=-change;
	   e+=var;
	  }
	  System.out.println(e);
	 }
}
