import p4.Car;
import p4.Plane;
import p4.Vehicle;

public class TestVehicle {
	public static void main(String args[])
	{
		Vehicle vehicle;
		vehicle= new Plane("plane","Boeing","10000","1000","1000","942");
		vehicle.move();
		vehicle.stop();
		vehicle.speedUp();
		vehicle.slowDown();
		vehicle= new Car("car","bench","100","56","120","80");
		vehicle.move();
		vehicle.stop();
		vehicle.speedUp();
		vehicle.slowDown();
	}
}