package MyPackage;

public class reverse {
	public static String charAtReverse (String s){
		   int length = s.length();
		   String reverse = " ";
		   for (int i = 0; i < length; i++) {
		    reverse = s.charAt(i)+reverse;//�ַ����л�ȡ�����ַ����ַ��ķŷ�
		   }
		   return reverse;
		  }
	public static void main(String args[])
	{
		System.out.println(charAtReverse("- .. .- . -.. --- - . -- --- .-.- ..-. . --."));
	}
}
