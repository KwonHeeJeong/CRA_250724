package mission2.strategy;
import mission2.Assemble;

import static mission2.Constant.*;
import static mission2.Constant.BrakeSystem_Q;
import static mission2.Constant.SteeringSystem_Q;

public class RunTestStrategy implements AssembleStrategy {
    public void selectSystem(int answer) {
        doRunTest(answer);
    }
    public void showMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }
    public boolean isValidRange(int ans) {
        if (ans < 0 || ans > 2) {
            System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
            return false;
        }
        return true;
    }

    public void doRunTest(int answer) {
        int[] stack = Assemble.getStack();
        if (answer == 1) {
            runProducedCar(stack);
            delay(1200);
        }
        else if (answer == 2) {
            System.out.println("Test...");
            delay(1500);
            testProducedCar(stack);
            delay(1200);
        }
    }

    protected void runProducedCar(int[] stack) {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (stack[Engine_Q] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[stack[CarType_Q]]);
        System.out.printf("Engine   : %s\n", engNames[stack[Engine_Q]]);
        System.out.printf("Brake    : %s\n",
                stack[BrakeSystem_Q]==1? "Mando":
                        stack[BrakeSystem_Q]==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                stack[SteeringSystem_Q]==1? "Bosch":"Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    protected boolean isValidCheck() {
        int[] stack = Assemble.getStack();
        if (isSedanAndContinental(stack)) return false;
        if (isSuvAndToyota(stack))       return false;
        if (isTruckAndWIA(stack))          return false;
        if (isTruckAndMando(stack))  return false;
        if (isOnlyBosch(stack)) return false;
        return true;
    }

    protected void testProducedCar(int[] stack) {
        if (isSedanAndContinental(stack)) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        }
        if (isSuvAndToyota(stack)) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        }
        else if (isTruckAndWIA(stack)) {
            fail("Truck에는 WIA엔진 사용 불가");
        }
        else if (isTruckAndMando(stack)) {
            fail("Truck에는 Mando제동장치 사용 불가");
        }
        else if (isOnlyBosch(stack)) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        }
        else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static boolean isOnlyBosch(int[] stack) {
        return stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S;
    }

    private static boolean isTruckAndMando(int[] stack) {
        return stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO;
    }

    private static boolean isTruckAndWIA(int[] stack) {
        return stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA;
    }

    private static boolean isSuvAndToyota(int[] stack) {
        return stack[CarType_Q] == SUV && stack[Engine_Q] == TOYOTA;
    }

    private static boolean isSedanAndContinental(int[] stack) {
        return stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL;
    }

    protected void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

}