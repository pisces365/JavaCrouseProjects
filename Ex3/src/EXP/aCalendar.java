package EXP;
import java.util.*;
public class aCalendar {
	public static void main(String args[])
	{
		Calendar ca = Calendar.getInstance();
		
		String year = String.format("%1$tY", ca);
		System.out.println("��ݣ�"+ year);
		
		String month = String.format("%1$tm", ca);
		System.out.println("�·ݣ�"+ month);
		
		int monthi = ca.get(Calendar.MONTH)+1;
		if(monthi>=1&&monthi<=3) System.out.println("���ȣ�"+1);
		else if(monthi>=4&&monthi<=6) System.out.println("���ȣ�"+2);
		else if(monthi>=7&&monthi<=9) System.out.println("���ȣ�"+3);
		else  System.out.println("���ȣ�"+4);
		
		int day = ca.get(Calendar.DAY_OF_WEEK);
		System.out.println("����������"+day);
		
		String date = String.format("%1$tY��%1$tm��%1$td��", ca);
		System.out.println("���ڣ�"+date);
		
		String time = String.format("%1$tH:%1$tM:%1$tS", ca);
		System.out.println("����ʱ�䣺"+time);
		
		String datetime = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", ca);
		System.out.println("���ں�ʱ�䣺"+datetime);
	}
}
