package EXP;
import java.util.*;
public class ParkingFeeCollection2 {
	Calendar inTime,outTime;//�������������
	long in,out,time,time_firstDay=0,time_lastDay=0;//����ȫ��ʱ�����in out������ʱ������time����һ��ʱ�䣬���һ��ʱ��
	long fee_firstDay,fee_other_day,fee_lastDay;//��һ����ã����������ã�������ͣһ����ܷ��ã������һ�����
	int flag = 0;//�ж�ͣһ���ͣ�������
	public ParkingFeeCollection2(Calendar in,Calendar out)
	{
		inTime = in;
		outTime = out;
		//������ͣһ��
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
		this.out = out.getTimeInMillis()/1000/60;//��¼ȫ�ֳ�ʱ�䣨���ӣ�//���д��error��(out.get(Calendar.YEAR)*365*30*24*60+out.get(Calendar.MONTH)*30*24*60+out.get(Calendar.DAY_OF_MONTH)*24*60+out.get(Calendar.HOUR)*60 + out.get(Calendar.MINUTE));
		this.in = in.getTimeInMillis()/1000/60;//��¼ȫ����ʱ�䣨���ӣ�//���д��error��(in.get(Calendar.YEAR)*365*30*24*60+in.get(Calendar.MONTH)*30*24*60+in.get(Calendar.DAY_OF_MONTH)*24*60+in.get(Calendar.HOUR)*60 + in.get(Calendar.MINUTE));
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
		
		if(flag == 0)//ʱ��Ϊһ��
		{
			if(inTime.get(Calendar.HOUR_OF_DAY)<8)//������ʱ����8hǰ
			{
				if(outTime.get(Calendar.HOUR_OF_DAY)<8)//������ʱ����8hǰ
				{
					fee_other_day = 4;//�����Ʒ�
				}
				else //������ʱ����8h��
				{
					fee_other_day = ((time_lastDay - 8*60)/30)*2 + 4;//Сʱ�Ʒ�+�����Ʒ�
					fee_other_day = (fee_other_day>16?16:fee_other_day);//�ж��Ƿ��ܷ��ó���16
				}
			}
			else if(inTime.get(Calendar.HOUR_OF_DAY)>=20) fee_other_day = 4;//�����Ʒ�
			else//������ʱ����8h-20h
			{
				if(outTime.get(Calendar.HOUR_OF_DAY)<20)//������ʱ����20hǰ
				{
					fee_other_day = (time/30)*2;//Сʱ�Ʒ�
					fee_other_day = (fee_other_day>16?16:fee_other_day);//�ж��Ƿ��ܷ��ó���16
				}
				else//������ʱ����20h��
				{
					fee_other_day = ((time-(time_lastDay - 20*60))/30)*2 + 4;//Сʱ�Ʒ�+�����Ʒ�
					fee_other_day = (fee_other_day>16?16:fee_other_day);//�ж��Ƿ��ܷ��ó���16
				}
			}
			return fee_other_day;
		}
		else//ͣ��ʱ�������
		{
			fee_other_day = ((time-time_firstDay-time_lastDay)/60/24)*16;//�м��������ķ���
		
			//�볡
			if(inTime.get(Calendar.HOUR_OF_DAY)<8)//������ʱ����8hǰ
			{
				fee_firstDay = 16;
			}
			else if(inTime.get(Calendar.HOUR_OF_DAY)>=20) //ͣ����20h���ð�4rmb��
				fee_other_day = 4;//�����Ʒ�
			else//������ʱ����8h-20h
			{
				fee_firstDay = ((time_firstDay-4*60)/30)*2 + 4;//Сʱ�Ʒ� ��ȥ�����Сʱ
				fee_firstDay = (fee_firstDay>16?16:fee_firstDay);//�ж��Ƿ��ܷ��ó���16
			}

			//����
			if(outTime.get(Calendar.HOUR_OF_DAY)<8)//������ʱ����8hǰ
			{
				fee_lastDay = 4;
			}
			else if(outTime.get(Calendar.HOUR_OF_DAY)<20)//������ʱ����8h-20h
			{
				fee_lastDay = ((time_lastDay - 8*60)/30)*2 + 4;//Сʱ�Ʒ�+�����Ʒ�
				fee_lastDay = (fee_lastDay>16?16:fee_lastDay);//�ж��Ƿ��ܷ��ó���16
			}
			else//ͣ����20h���ð�16rmb��
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
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,13,12,15);
		out.set(2014,10-1,8,13,48,42);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,14,52,17);
		out.set(2014,10-1,8,16,28,22);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,15,12,15);
		out.set(2014,10-1,8,20,38,49);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,07,29,52);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,20,12,15);
		out.set(2014,10-1,9,7,45,26);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,16,12,15);
		out.set(2014,10-1,9,13,49,53);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
		
		in.set(2014,10-1,8,17,12,15);
		out.set(2014,10-1,11,15,12,12);
		pk = new ParkingFeeCollection2(in,out);
		System.out.println("�볡ʱ�䣺"+pk.intime()+" ����ʱ�䣺"+pk.outtime()+" ����Ϊ��"+pk.calculate());
	}
}
