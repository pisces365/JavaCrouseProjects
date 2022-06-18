package EXP;
import java.util.*;
public class ParkingFeeCollection2 {
	Calendar inTime,outTime;//备用日历类参数
	long in,out,time,time_firstDay=0,time_lastDay=0;//进出全局时间参数in out，进出时间差参数time，第一天时间，最后一天时间
	long fee_firstDay,fee_other_day,fee_lastDay;//第一天费用，整天数费用（亦用作停一天的总费用），最后一天费用
	int flag = 0;//判断停一天或停多天参数
	public ParkingFeeCollection2(Calendar in,Calendar out)
	{
		inTime = in;
		outTime = out;
		//当不是停一天
		if(!(in.get(Calendar.DATE)==out.get(Calendar.DATE)&&in.get(Calendar.MONTH)==out.get(Calendar.MONTH)&&in.get(Calendar.YEAR)==out.get(Calendar.YEAR)))
		{
			flag = 1;
			time_firstDay = 24*60-(in.get(Calendar.HOUR_OF_DAY)*60 + in.get(Calendar.MINUTE));
			time_lastDay = out.get(Calendar.HOUR_OF_DAY)*60 + out.get(Calendar.MINUTE);
		}
		else
		{
			time_firstDay = (in.get(Calendar.HOUR_OF_DAY)*60 + in.get(Calendar.MINUTE));
			time_lastDay = out.get(Calendar.HOUR_OF_DAY)*60 + out.get(Calendar.MINUTE);
		}
		//System.out.println(time_lastDay);
		this.out = out.getTimeInMillis()/1000/60;//记录全局出时间（分钟）//最初写法error：(out.get(Calendar.YEAR)*365*30*24*60+out.get(Calendar.MONTH)*30*24*60+out.get(Calendar.DAY_OF_MONTH)*24*60+out.get(Calendar.HOUR)*60 + out.get(Calendar.MINUTE));
		this.in = in.getTimeInMillis()/1000/60;//记录全局入时间（分钟）//最初写法error：(in.get(Calendar.YEAR)*365*30*24*60+in.get(Calendar.MONTH)*30*24*60+in.get(Calendar.DAY_OF_MONTH)*24*60+in.get(Calendar.HOUR)*60 + in.get(Calendar.MINUTE));
		time = this.out - this.in;
		this.fee_firstDay = 0;
		fee_other_day = 0;
		fee_lastDay = 0;
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
		
		if(flag == 0)//时间为一天
		{
			if(inTime.get(Calendar.HOUR_OF_DAY)<8)//若进场时间在8h前
			{
				if(outTime.get(Calendar.HOUR_OF_DAY)<8)//若出场时间在8h前
				{
					fee_other_day = 4;//次数计费
				}
				else //若出场时间在8h后
				{
					fee_other_day = ((time_lastDay - 8*60)/30)*2 + 4;//小时计费+次数计费
					fee_other_day = (fee_other_day>16?16:fee_other_day);//判断是否总费用超过16
				}
			}
			else if(inTime.get(Calendar.HOUR_OF_DAY)>=20) fee_other_day = 4;//次数计费
			else//若进场时间在8h-20h
			{
				if(outTime.get(Calendar.HOUR_OF_DAY)<20)//若出场时间在20h前
				{
					fee_other_day = (time/30)*2;//小时计费
					fee_other_day = (fee_other_day>16?16:fee_other_day);//判断是否总费用超过16
				}
				else//若出场时间在20h后
				{
					fee_other_day = ((time-(time_lastDay - 20*60))/30)*2 + 4;//小时计费+次数计费
					fee_other_day = (fee_other_day>16?16:fee_other_day);//判断是否总费用超过16
				}
			}
			return fee_other_day;
		}
		else//停车时间跨天数
		{
			fee_other_day = ((time-time_firstDay-time_lastDay)/60/24)*16;//中间整天数的费用
		
			//入场
			if(inTime.get(Calendar.HOUR_OF_DAY)<8)//若进场时间在8h前
			{
				fee_firstDay = 16;
			}
			else if(inTime.get(Calendar.HOUR_OF_DAY)>=20) //停车到20h费用按4rmb算
				fee_other_day = 4;//次数计费
			else//若进场时间在8h-20h
			{
				fee_firstDay = ((time_firstDay-4*60)/30)*2 + 4;//小时计费 略去最后四小时
				fee_firstDay = (fee_firstDay>16?16:fee_firstDay);//判断是否总费用超过16
			}

			//出场
			if(outTime.get(Calendar.HOUR_OF_DAY)<8)//若出场时间在8h前
			{
				fee_lastDay = 4;
			}
			else if(outTime.get(Calendar.HOUR_OF_DAY)<20)//若出场时间在8h-20h
			{
				fee_lastDay = ((time_lastDay - 8*60)/30)*2 + 4;//小时计费+次数计费
				fee_lastDay = (fee_lastDay>16?16:fee_lastDay);//判断是否总费用超过16
			}
			else//停车到20h费用按16rmb算
			{
				fee_lastDay = 16;
			}
			
			return fee_firstDay + fee_other_day + fee_lastDay;
		}
	}
	
	public static void main(String args[])
	{
		Calendar in = Calendar.getInstance();
		Calendar out = Calendar.getInstance();
		ParkingFeeCollection2 pk;
		
		
		in.set(2014,10-1,8,12,2,13);
		out.set(2014,10-1,8,12,13,56);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,13,12,15);
		out.set(2014,10-1,8,13,48,42);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,14,52,17);
		out.set(2014,10-1,8,16,28,22);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,15,12,15);
		out.set(2014,10-1,8,20,38,49);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,07,29,52);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,20,12,15);
		out.set(2014,10-1,9,7,45,26);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,13,49,53);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
		
		in.set(2014,10-1,8,17,12,15);
		out.set(2014,10-1,11,15,12,12);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("入场时间："+pk.intime()+" 出场时间："+pk.outtime()+" 费用为："+pk.calculate());
	}
}
