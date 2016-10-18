package unitpack;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import etc.Calculator;

public class CalculatorTest {
	Calculator calculator = new Calculator();

	@Test
	public void testPlus() {
		int a = 8, b = 8;
		assertEquals(a, b);
		
		assertTrue(calculator.plus(8, 4) == 12);
		
		int[] arr = {0,0};
		int[] arr2 = new int[2];
		assertArrayEquals(arr, arr2);
	}

	@Test(timeout=1000)
	public void testMinus() {
		assertTrue(calculator.minus(6, 2) == 4);
		for (int i = 0; i < 1000000; i++) {
			System.out.println(i + " ");
		}
	}

	@Ignore
	@Test
	public void testMulti() {
//		fail("Not yet implemented");
	}

	@Test
	public void testDivide() {
		assertTrue(calculator.divide(10, 4) == 2.5);
	}

}
