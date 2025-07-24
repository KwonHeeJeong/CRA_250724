package mission2.strategy;

import mission2.Assemble;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RunTestStrategyTest {

    final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }
    @Test
    void testRunProducedCar_True() {
        int[] stack = new int[] { 1, 1, 1, 1, 1 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.runProducedCar(stack);
        verify(runTestStrategySpy, times(1)).isValidCheck();
    }
    @Test
    void testRunProducedCar_False() {
        int[] stack = new int[] { 2, 2, 2, 1, 1 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.runProducedCar(stack);
        verify(runTestStrategySpy, times(1)).isValidCheck();
    }
    @Test
    void testRunProducedCar_EngineFalse() {
        int[] stack = new int[] { 1, 4, 2, 2, 2 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.runProducedCar(stack);
        verify(runTestStrategySpy, times(1)).isValidCheck();
    }


    @Test
    void testTestProducedCar() {
        int[] stack = new int[] { 1, 1, 1, 1, 1 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        assertEquals("자동차 부품 조합 테스트 결과 : PASS", outputStreamCaptor.toString().trim());
    }
    @Test
    void testTestProducedCar_SedanAndContinental() {
        int[] stack = new int[] { 1, 2, 2, 2, 2 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        verify(runTestStrategySpy, times(1)).fail("Sedan에는 Continental제동장치 사용 불가");
    }
    @Test
    void testTestProducedCar_SuvAndToyota() {
        int[] stack = new int[] { 2, 2, 2, 2, 2 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        verify(runTestStrategySpy, times(1)).fail("SUV에는 TOYOTA엔진 사용 불가");
    }
    @Test
    void testTestProducedCar_TruckAndWIA() {
        int[] stack = new int[] { 3, 3, 3, 3, 3 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        verify(runTestStrategySpy, times(1)).fail("Truck에는 WIA엔진 사용 불가");
    }
    @Test
    void testTestProducedCar_TruckAndMando() {
        int[] stack = new int[] { 3, 1, 1, 1, 1 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        verify(runTestStrategySpy, times(1)).fail("Truck에는 Mando제동장치 사용 불가");
    }
    @Test
    void testTestProducedCar_OnlyBosch() {
        int[] stack = new int[] { 1, 1, 3, 2, 2 };
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar(stack);
        verify(runTestStrategySpy, times(1)).fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
    }
}