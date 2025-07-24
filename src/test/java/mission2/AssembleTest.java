package mission2;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static mission2.Constant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssembleTest {
    @Test
    void testAssemble() {
        Assemble assemble = new Assemble();
        int actual = assemble.backStep(Run_Test);
        assertEquals(CarType_Q, actual);
    }

    @Test
    void testGetStack() {
        Assemble assemble = mock(Assemble.class);
        int actual = assemble.getStack().length;
        assertEquals(5, actual);
    }

    @Test
    void testDoAssemble_exit() {
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);

        Assemble assembleSpy = spy(new Assemble());
        assembleSpy.doAssemble();

        assertEquals(CarType_Q, assembleSpy.getStack()[0]);
    }

    @Test
    void testBackStep_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.backStep(Engine_Q);
        assertEquals(CarType_Q, actual);
    }
    @Test
    void testBackStep_Run_Test() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        int actual = assembleSpy.backStep(Run_Test);
        assertEquals(CarType_Q, actual);
    }
    @Test
    void testBackStep_Run_Others() {
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
    void testShowMenuBrakeSystemStrategy() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.startCurrentStep(BrakeSystem_Q);
        verify(assembleSpy, times(1)).showMenuForUser(BrakeSystem_Q);
    }
    @Test
    void testShowMenuEngineStrategy() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.startCurrentStep(Engine_Q);
        verify(assembleSpy, times(1)).showMenuForUser(Engine_Q);
    }
    @Test
    void testShowMenuSteeringSystemStrategy() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.startCurrentStep(SteeringSystem_Q);
        verify(assembleSpy, times(1)).showMenuForUser(SteeringSystem_Q);
    }

    @Test
    void testShowMenuForUser_Error() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.startCurrentStep(-1);
        verify(assembleSpy, times(0)).showMenuForUser(Run_Test);
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

    @Test
    void testIsValidRange() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(-1, -1);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_CarType_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(CarType_Q, 1);
        assertTrue(actual);
    }
    @Test
    void testIsValidRange_CarType_SmallFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(CarType_Q, -1);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_CarType_LargeFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(CarType_Q, 5);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_BrakeSystem_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(BrakeSystem_Q, 1);
        assertTrue(actual);
    }
    @Test
    void testIsValidRange_BrakeSystem_SmallFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(BrakeSystem_Q, -1);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_BrakeSystem_LargeFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(BrakeSystem_Q, 5);
        assertFalse(actual);
    }

    @Test
    void testIsValidRange_Engine_Q_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(Engine_Q, 1);
        assertTrue(actual);
    }
    @Test
    void testIsValidRange_Engine_Q_SmallFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(Engine_Q, -1);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_Engine_Q_LargeFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(Engine_Q, 5);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_SteeringSystem_Q_True() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(SteeringSystem_Q, 1);
        assertTrue(actual);
    }
    @Test
    void testIsValidRange_SteeringSystem_Q_SmallFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(SteeringSystem_Q, -1);
        assertFalse(actual);
    }
    @Test
    void testIsValidRange_SteeringSystem_Q_LargeFalse() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        boolean actual = assembleSpy.isValidRange(SteeringSystem_Q, 5);
        assertFalse(actual);
    }
    @Test
    void testDoActionOfCurrentStep_CarType_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(CarType_Q, 1);

        int actual = assembleSpy.getStack()[CarType_Q];
        assertEquals(1, actual);
    }
    @Test
    void testDoActionOfCurrentStep_BrakeSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(BrakeSystem_Q, 1);

        int actual = assembleSpy.getStack()[BrakeSystem_Q];
        assertEquals(1, actual);
    }
    @Test
    void testDoActionOfCurrentStep_Engine_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(Engine_Q, 1);

        int actual = assembleSpy.getStack()[Engine_Q];
        assertEquals(1, actual);
    }
    @Test
    void testDoActionOfCurrentStep_RunTest_Q_Run() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(Run_Test, 1);

        int actual = assembleSpy.getStack()[Run_Test];
        assertEquals(0, actual);
    }
    @Test
    void testDoActionOfCurrentStep_SteeringSystem_Q() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(SteeringSystem_Q, 1);

        int actual = assembleSpy.getStack()[SteeringSystem_Q];
        assertEquals(1, actual);
    }
    @Test
    void testDoActionOfCurrentStep_RunTest_Q_Test() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(Run_Test, 2);

        int actual = assembleSpy.getStack()[Run_Test];
        assertEquals(0, actual);
    }

    @Test
    void testDoActionOfCurrentStep_Error() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(-1, 1);

        int actual = assembleSpy.getStack()[Engine_Q];
        assertEquals(0, actual);
    }
    @Test
    void testDoActionOfCurrentStep_Run_Test() {
        Assemble assembleSpy = Mockito.spy(new Assemble());
        assembleSpy.doActionOfCurrentStep(-1, 1);

        int actual = assembleSpy.getStack()[Engine_Q];
        assertEquals(0, actual);
    }
}