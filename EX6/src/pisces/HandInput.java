package pisces;

import java.io.*;

public class HandInput {
	public static void main(String [] args)throws IOException//����Ҫ�׳��쳣
	{
		File f = new File("f.txt");
		if(f.exists()==true)
		{	
			System.out.println("�ļ��Ѵ��ڣ�������ָ��������ļ���");
			return;
		}
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bwr = new BufferedWriter(new FileWriter("f.txt"));
		String input = null;
		while(!(input=bfr.readLine()).equals("end#"))//ÿ�ζ���һ�У�ֱ������end#
		{
			bwr.write(input);
			bwr.newLine();
		}
		bfr.close();
		bwr.close();
	}
}
