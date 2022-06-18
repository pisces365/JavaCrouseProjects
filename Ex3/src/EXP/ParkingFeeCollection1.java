package EXP;
import java.util.*;
public class ParkingFeeCollection1 {
	Calendar inTime,outTime;//备用日历类参数
	long in,out,time,time_firstDay=0,time_lastDay=0;
	long fee_firstDay,fee_other_day,fee_lastDay;
	int flag = 0;
	public ParkingFeeCollection1(Calendar in,Calendar out)
	{
		inTime = in;
		outTime = out;
		if(!(in.get(Calendar.DATE)==out.get(Calendar.DATE)&&in.get(Calendar.MONTH)==out.get(Calendar.MONTH)&&in.get(Calendar.YEAR)==out.get(Calendar.YEAR)))
		{
			flag = 1;
			time_firstDay = 24*60-(in.get(Calendar.HOUR_OF_DAY)*60 + in.get(Calendar.MINUTE));
			time_lastDay = out.get(Calendar.HOUR_OF_DAY)*60 + out.get(Calendar.MINUTE);
		}
		//System.out.println(time_lastDay);
		this.out = out.getTimeInMillis()/1000/60;//分钟(out.get(Calendar.YEAR)*365*30*24*60+out.get(Calendar.MONTH)*30*24*60+out.get(Calendar.DAY_OF_MONTH)*24*60+out.get(Calendar.HOUR)*60 + out.get(Calendar.MINUTE));
		this.in = in.getTimeInMillis()/1000/60;//(in.get(Calendar.YEAR)*365*30*24*60+in.get(Calendar.MONTH)*30*24*60+in.get(Calendar.DAY_OF_MONTH)*24*60+in.get(Calendar.HOUR)*60 + in.get(Calendar.MINUTE));
		time = this.out - this.in;
		this.fee_firstDay = 0;
		fee_other_day = 0;
		fee_lastDay = 0;
		//System.out.println(this.out+" "+this.in+" "+time%3600);
	}
	
	public String intime()
	{
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", inTime);
	}
	
	public String outtime()
	{
		return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", outTime);
	}
	
	public long calculate()
	{
		
		if(flag == 0)
		{
			fee_firstDay = (time/60)*5;
			if(time%60>15) fee_firstDay+=5;
			if(fee_firstDay>30) fee_firstDay=30;
			return fee_firstDay;
		}
		else
		{
			fee_other_day = ((time-time_firstDay-time_lastDay)/60/24)*30;
		
			//System.out.println((time-time_firstDay-time_lastDay)/60/24);
		
			fee_firstDay = (time_firstDay/60)*5;
			if(time_firstDay%60>15) fee_firstDay+=5;
			if(fee_firstDay>30) fee_firstDay=30;
			//System.out.println(fee_firstDay);
		
			fee_lastDay = (time_lastDay/60)*5;
			if(time_lastDay%60>15) fee_lastDay+=5;
			if(fee_lastDay>30) fee_lastDay=30;
			//System.out.println(time_lastDay);
			return fee_firstDay+fee_other_day+fee_lastDay;
		}
	}
	
	public static void main(String args[])
	{
		Calendar in = Calendar.getInstance();
		Calendar out = Calendar.getInstance();
		ParkingFeeCollection1 pk;
		
		
		in.set(2014,10-1,8,12,2,13);
		out.set(2014,10-1,8,12,13,56);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,13,12,15);
		out.set(2014,10-1,8,13,48,42);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,14,52,17);
		out.set(2014,10-1,8,16,28,22);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,15,12,15);
		out.set(2014,10-1,8,20,38,49);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,07,29,52);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,20,12,15);
		out.set(2014,10-1,9,7,45,26);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,13,49,53);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,17,12,15);
		out.set(2014,10-1,11,15,12,12);
		pk = new ParkingFeeCollection1(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
	}
}
