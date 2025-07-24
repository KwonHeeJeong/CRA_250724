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
    void testRunProducedCar() {
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.runProducedCar();
        verify(runTestStrategySpy, times(1)).isValidCheck();
    }

    @Test
    void testTestProducedCar() {
        RunTestStrategy runTestStrategySpy = Mockito.spy(new RunTestStrategy());
        runTestStrategySpy.testProducedCar();
        assertEquals("자동차 부품 조합 테스트 결과 : PASS", outputStreamCaptor.toString().trim());
    }
}