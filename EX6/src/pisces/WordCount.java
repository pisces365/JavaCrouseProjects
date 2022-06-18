package pisces;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordCount {
	public static void main(String [] args)throws IOException
	{
		int numberRecord = 0;//记录读取次数的变量
		String str;//记录每一次读入数据的临时变量
		List<String> arr = new ArrayList<>();//利用arraylist存放文章中的所有单词
		File fl = new File("article.txt");//利用File文件打开article文件
		if(fl.exists()==false)
			fl.createNewFile();//如果文件不存在就创建新的文件
		BufferedReader bfr = new BufferedReader (new FileReader(fl));//利用缓冲流读入文件，FileReader可避免中文字符乱码
		str = bfr.readLine();
		while(str!=null)//不要用bfr.readLine()作为判空条件
		{
			String spl[]= str.split(" ");//将一行英文句子划分为若干单词
			for(int i=0;i<spl.length;++i)
			arr.add(spl[i]);//将每个单词添加到arraylist中
			str = bfr.readLine();
		}
		bfr.close();
		String record1,record2;
		for(int i=0;i<arr.size();++i)
		{
			record1 = arr.get(i);//record1获取数组表中的单词
			record2 = record1.toLowerCase();//record2记录单词字母转换为小写后的数据
			arr.set(i, record2);//将数组表中的数据重置为无大写的状态
			if(record2.indexOf("hello")!=-1)
			{
				numberRecord++;//只要读到就记录一下
			}
		}
		System.out.println("单词hello在文章article中出现的次数为：" +numberRecord);
	}
}
