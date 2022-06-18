package pisces;

import java.io.*;

public class HandInput {
	public static void main(String [] args)throws IOException//必须要抛出异常
	{
		File f = new File("f.txt");
		if(f.exists()==true)
		{	
			System.out.println("文件已存在，请重新指定保存的文件！");
			return;
		}
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bwr = new BufferedWriter(new FileWriter("f.txt"));
		String input = null;
		while(!(input=bfr.readLine()).equals("end#"))//每次都读一行，直到遇到end#
		{
			bwr.write(input);
			bwr.newLine();
		}
		bfr.close();
		bwr.close();
	}
}
