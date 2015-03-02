import java.lang.reflect.*;

class Client{
	public String imie;
	public int age = 20;	//typ prosty
//	public Integer age = 20;	//referencja
	
	public Client(String imie){
		this.imie = imie;
	}
	
	public int changeAge(int i){
		int newAge = age + i;
		
		return newAge;
	}
}

public class Main {
	
	public static int repetitions = 100000000;
	
	public static void readReflect(Client a) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field f = a.getClass().getDeclaredField("age");
		int temp;
		for(int i = 0; i < repetitions; i++){
			//temp = f.getInt(a);
			temp = (int) f.get(a);
		}
	}
	
	public static void readRegular(Client a){
		int temp;
		for(int i = 0; i < repetitions; i++){
			temp = a.age;
		}
	}
	
	public static void writeReflect(Client a) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Field f = a.getClass().getDeclaredField("age");
		for(int i = 0; i < repetitions; i++){
			f.set(a, 30);
		}
	}
	
	public static void writeRegular(Client a){
		for(int i = 0; i < repetitions; i++){
			a.age = 10;
		}
	}
	
	public static void methodReflect(Client a) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method m = a.getClass().getMethod("changeAge", int.class);
		for(int i = 0; i < repetitions; i++){
			Object o = m.invoke(a, 10);
		}
	}
	
	public static void methodRegular(Client a){
		for(int i = 0; i < repetitions; i++){
			a.changeAge(10);
		}
	}
	
	public static void main(String args[]) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{
		Client a = new Client("Jakub");
		
		//Odczyt z reflection
		long startTime = System.currentTimeMillis();
		readReflect(a);
		long endTime = System.currentTimeMillis();
		
		long duration1 = (endTime - startTime);

		//Zwykly odczyt
		startTime = System.currentTimeMillis();
		readRegular(a);
		endTime = System.currentTimeMillis();

		long duration2 = (endTime - startTime);
		
		//Zapis z reflection
		startTime = System.currentTimeMillis();
		writeReflect(a);
		endTime = System.currentTimeMillis();

		long duration3 = (endTime - startTime);
		
		//Zapis bez reflection
		startTime = System.currentTimeMillis();
		writeRegular(a);
		endTime = System.currentTimeMillis();

		long duration4 = (endTime - startTime);
		
		//Wywo³anie metody z reflection
		startTime = System.currentTimeMillis();
		methodReflect(a);
		endTime = System.currentTimeMillis();

		long duration5 = (endTime - startTime);
		
		//Wywo³anie metody bez reflection
		startTime = System.currentTimeMillis();
		methodRegular(a);
		endTime = System.currentTimeMillis();

		long duration6 = (endTime - startTime);
		
		System.out.println("Iloœæ wykonanych powtórzeñ:		" + repetitions);

		System.out.println("Czas odczytu z reflection:		" + duration1 + " milisekund");
		System.out.println("Czas odczytu bez reflection:		" + duration2 + " milisekund");
		System.out.println("Czas zapisu z reflection:		" + duration3 + " milisekund");
		System.out.println("Czas zapisu bez reflection:		" + duration4 + " milisekund");
		System.out.println("Czas wywo³ania metody z reflection:	" + duration5 + " milisekund");
		System.out.println("Czas wywo³ania metody bez reflection:	" + duration6 + " milisekund");
	}
}
