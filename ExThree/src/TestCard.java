import p2.StudentCard;
import p2.TeacherCard;

public class TestCard {
	public static void main(String args[])
	{
		StudentCard sc = new StudentCard("001","��ͬѧ","2001.4.1","�����","�������","2019.9.1","��ע��");
		sc.print();
		TeacherCard tc = new TeacherCard("002","����ʦ","1980.4.1","���ܼ�������","����","2000.9.1");
		tc.print();
	}
}
