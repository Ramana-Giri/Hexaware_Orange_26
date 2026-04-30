package day3;

import org.testng.annotations.Test;

public class PriorityTest {
	@Test(priority=0)
	public static void test0(){
		System.out.println("Priority : 0");
	}
	
	@Test(priority=1)
	public static void test1(){
		System.out.println("Priority : 1");
	}
	
	@Test(priority=2)
	public static void test2(){
		System.out.println("Priority : 2");
	}
	
	@Test(priority=3)
	public static void test3(){
		System.out.println("Priority : 3");
	}
	
	@Test
	public static void ztest4(){
		System.out.println("Priority : nill");
	}
	
	@Test(priority=4)
	public static void test5(){
		System.out.println("Priority : 4");
	}
	
	@Test()
	 public void utest()
	 {
	 System.out.println("Verify Test");
	 }

	@Test()
	public void Add()
	{
	System.out.println("Addition");
	}

	@Test()
	public void Division()
	{
	System.out.println("Division");
	}

	@Test()
	public void Multiplication()
	{
	System.out.println("Multilication");
	}
}
