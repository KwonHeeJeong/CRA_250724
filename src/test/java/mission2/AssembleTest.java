package mission2;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static mission2.Constant.*;

class AssembleTest {

    @Test
    public void testDoActionOfCurrentStep_RunTest() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(Run_Test, 1);
        verify(assembleSpy, times(1)).doRunTest(1);
    }
    @Test
    public void testDoActionOfCurrentStep_CarType_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(CarType_Q, 1);
        verify(assembleSpy, times(1)).selectCarType(1);
    }
    @Test
    public void testDoActionOfCurrentStep_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(Engine_Q, 1);
        verify(assembleSpy, times(1)).selectEngine(1);
    }
    @Test
    public void testDoActionOfCurrentStep_BrakeSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(BrakeSystem_Q, 1);
        verify(assembleSpy, times(1)).selectBrakeSystem(1);
    }
    @Test
    public void testDoActionOfCurrentStep_SteeringSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(SteeringSystem_Q, 1);
        verify(assembleSpy, times(1)).selectSteeringSystem(1);
    }
    @Test
    public void testDoActionOfCurrentStep_OutOfRange() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(-1, 1);
        verify(assembleSpy, times(1)).delay(800);
    }

    @Test
    void testDoRunTest_answer1() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doRunTest( 1);
        verify(assembleSpy, times(1)).runProducedCar();
    }
    @Test
    void testDoRunTest_answer2() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doRunTest( 2);
        verify(assembleSpy, times(1)).testProducedCar();
    }
    @Test
    void testDoRunTest_OutOfRange() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doRunTest( 3);
        verify(assembleSpy, times(0)).runProducedCar();
    }

    @Test
    void testBackStep_RunTest() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.backStep(Run_Test);
        assertEquals(CarType_Q, actual);
    }
    @Test
    void testBackStep_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.backStep(Engine_Q);
        assertEquals(CarType_Q, actual);
    }
    @Test
    void testBackStep_OutOfRange() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.backStep(-1);
        assertEquals(-1, actual);
    }

    @Test
    void testNextStep_RunTest() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.nextStep(Run_Test);
        assertEquals(Run_Test, actual);
    }
    @Test
    void testNextStep_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.nextStep(Engine_Q);
        assertEquals(BrakeSystem_Q, actual);
    }

    @Test
    void testIsInvalidInput_False() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isInvalidInput("1", Run_Test);
        assertFalse(actual);
    }
    @Test
    void testIsInvalidInput_FormatError() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isInvalidInput("a", Run_Test);
        assertTrue(actual);
    }
    @Test
    void testIsInvalidInput_RangeError() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isInvalidInput("5", Run_Test);
        assertTrue(actual);
    }

    @Test
    void testStartCurrentStep() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.startCurrentStep(Run_Test);
        verify(assembleSpy, times(1)).showMenuForUser(Run_Test);
    }

    @Test
    void testShowMenuForUser_CarType_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.showMenuForUser(CarType_Q);
        verify(assembleSpy, times(1)).showCarTypeMenu();
    }
    @Test
    void testShowMenuForUser_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.showMenuForUser(Engine_Q);
        verify(assembleSpy, times(1)).showEngineMenu();
    }
    @Test
    void testShowMenuForUser_BrakeSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.showMenuForUser(BrakeSystem_Q);
        verify(assembleSpy, times(1)).showBrakeMenu();
    }
    @Test
    void testShowMenuForUser_SteeringSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.showMenuForUser(SteeringSystem_Q);
        verify(assembleSpy, times(1)).showSteeringMenu();
    }
    @Test
    void testShowMenuForUser_Run_Test() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.showMenuForUser(Run_Test);
        verify(assembleSpy, times(1)).showRunTestMenu();
    }

    @Test
    void testIsInvalidInputFormat_False() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isInvalidInputFormat("5");
        assertFalse(actual);
    }
    @Test
    void testIsInvalidInputFormat_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isInvalidInputFormat("a");
        assertTrue(actual);
    }

    @Test
    void testGetInputBuffer() {
        String input = "test";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner sc = new Scanner(System.in);

        Assemble assembleSpy = Mockito.spy(new Assemble());
        String actual = assembleSpy.getInputBuffer(sc);
        assertEquals(input, actual);
    }

    @Test
    void testIsExitInput_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isExitInput("exit");
        assertTrue(actual);
    }
    @Test
    void testIsExitInput_False() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isExitInput("a");
        assertFalse(actual);
    }

}