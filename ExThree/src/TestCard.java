import p2.StudentCard;
import p2.TeacherCard;

public class TestCard {
	public static void main(String args[])
	{
		StudentCard sc = new StudentCard("001","杨同学","2001.4.1","计算机","软件工程","2019.9.1","已注册");
		sc.print();
		TeacherCard tc = new TeacherCard("002","周老师","1980.4.1","智能计算中心","教授","2000.9.1");
		tc.print();
	}
}
