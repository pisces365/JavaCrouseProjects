package EXP;
import java.util.*;
public class aCalendar {
	public static void main(String args[])
	{
		Calendar ca = Calendar.getInstance();
		
		String year = String.format("%1$tY", ca);
		System.out.println("年份："+ year);
		
		String month = String.format("%1$tm", ca);
		System.out.println("月份："+ month);
		
		int monthi = ca.get(Calendar.MONTH)+1;
		if(monthi>=1&&monthi<=3) System.out.println("季度："+1);
		else if(monthi>=4&&monthi<=6) System.out.println("季度："+2);
		else if(monthi>=7&&monthi<=9) System.out.println("季度："+3);
		else  System.out.println("季度："+4);
		
		int day = ca.get(Calendar.DAY_OF_WEEK);
		System.out.println("今天是星期"+day);
		
		String date = String.format("%1$tY年%1$tm月%1$td日", ca);
		System.out.println("日期："+date);
		
		String time = String.format("%1$tH:%1$tM:%1$tS", ca);
		System.out.println("北京时间："+time);
		
		String datetime = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", ca);
		System.out.println("日期和时间："+datetime);
	}
}
