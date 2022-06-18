package EXP;

import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {
	public static int maxElement(int a[])
	{
		int max=a[0];
		for(int i=1;i<a.length;i++)
		{
			if(a[i]>max) max=a[i];
		}
		return max;
	}
	
	public static int average(int a[])
	{
		int ave=0;
		for(int i=0;i<a.length;++i)
		{
			ave+=a[0];
		}
		ave/=a.length;
		return ave;
	}
	
	public static int search(int a[],int k)
	{
		int i;
		for(i=0;i<a.length;++i)
		{
			if(a[i]==k) return (i+1);
		}
		return -1;
	}
	
	public static void main (String[] args)
	{
		Set<Integer> set=new HashSet<Integer>();
		while(true){
			set.add((int)(Math.random()*40+60));
			if(set.size()==20)
				break;
		}
		int a[]=new int [20];
		int add = 0;
		for(int i:set)
		{
			a[add] = i;
			add++;
		}
		System.out.println("����Ϊ��������Ԫ�أ�");
		for(int i=0;i<a.length;++i)
		{
			System.out.print(a[i]+" ");
		}
		System.out.println();
		System.out.println("����Ԫ�����ֵΪ��"+ArrayUtil.maxElement(a));
		System.out.println("����Ԫ��ƽ��ֵΪ��"+ArrayUtil.average(a));
		System.out.println("����Ԫ��93λ�ڵ� "+ArrayUtil.search(a,93)+" λ��-1��ʾδ�ҵ���");
	}
}
