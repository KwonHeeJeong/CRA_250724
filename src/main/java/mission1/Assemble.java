package mission1;

import java.util.Scanner;

public class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Q      = 0;
    private static final int Engine_Q       = 1;
    private static final int BrakeSystem_Q  = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test       = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;
    public static final int INPUT_BACK = 0;

    private static int[] stack = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int currentStep = CarType_Q;

        while (true) {
            startCurrentStep(currentStep);

            String buf = getInputBuffer(sc);
            if (isExitInput(buf)) break;
            if (isInvalidInput(buf, currentStep)) continue;

            int answer = Integer.parseInt(buf);
            if (answer == INPUT_BACK) {
                currentStep = backStep(currentStep);
                continue;
            }

            doActionOfCurrentStep(currentStep, answer);
            currentStep = nextStep(currentStep);
        }
        sc.close();
    }

    private static void doActionOfCurrentStep(int currentStep, int answer) {
        switch (currentStep) {
            case Run_Test:
                doRunTest(answer);
                break;
            case CarType_Q:
                selectCarType(answer);
                break;
            case Engine_Q:
                selectEngine(answer);
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(answer);
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(answer);
                break;
        }
        delay(800);
    }

    private static void doRunTest(int answer) {
        if (answer == 1) {
            runProducedCar();
            delay(1200);
        } else if (answer == 2) {
            System.out.println("Test...");
            delay(1500);
            testProducedCar();
            delay(1200);
        }
    }

    private static int backStep(int currentStep) {
        if (currentStep == Run_Test) {
            currentStep = CarType_Q;
        } else if (currentStep > CarType_Q) {
            currentStep--;
        }
        return currentStep;
    }

    private static int nextStep(int currentStep) {
        if (currentStep < Run_Test) {
            currentStep++;
        }
        return currentStep;
    }

    private static boolean isInvalidInput(String buf, int currentStep) {
        if (isInvalidInputFormat(buf)) return true;
        if (isInvalidInputRange(currentStep, buf)) return true;
        return false;
    }

    private static boolean isInvalidInputRange(int currentStep, String buf) {
        if (!isValidRange(currentStep, Integer.parseInt(buf))) {
            delay(800);
            return true;
        }
        return false;
    }

    private static void startCurrentStep(int currentStep) {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
        showMenuForUser(currentStep);
    }

    private static void showMenuForUser(int currentStep) {
        switch (currentStep) {
            case CarType_Q:
                showCarTypeMenu(); break;
            case Engine_Q:
                showEngineMenu(); break;
            case BrakeSystem_Q:
                showBrakeMenu(); break;
            case SteeringSystem_Q:
                showSteeringMenu(); break;
            case Run_Test:
                showRunTestMenu(); break;
        }
    }

    private static boolean isInvalidInputFormat(String buf) {
        if (!buf.matches("[0-9]+")) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return true;
        }
        return false;
    }

    private static String getInputBuffer(Scanner sc) {
        System.out.print("INPUT > ");
        String buf = sc.nextLine().trim();
        return buf;
    }

    private static boolean isExitInput(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    private static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }
    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }
    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }
    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }
    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CarType_Q:
                if (!isCarTypeRange(ans)) return false;
                break;
            case Engine_Q:
                if (!isEngineRange(ans)) return false;
                break;
            case BrakeSystem_Q:
                if (!isBreakSystemRange(ans)) return false;
                break;
            case SteeringSystem_Q:
                if (!isSteeringSystemRange(ans)) return false;
                break;
            case Run_Test:
                if (!isRunTestRange(ans)) return false;
                break;
        }
        return true;
    }

    private static boolean isRunTestRange(int ans) {
        if (ans < 0 || ans > 2) {
            System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
            return false;
        }
        return true;
    }

    private static boolean isSteeringSystemRange(int ans) {
        if (ans < 0 || ans > 2) {
            System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
            return false;
        }
        return true;
    }

    private static boolean isBreakSystemRange(int ans) {
        if (ans < 0 || ans > 3) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    private static boolean isEngineRange(int ans) {
        if (ans < 0 || ans > 4) {
            System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
            return false;
        }
        return true;
    }

    private static boolean isCarTypeRange(int ans) {
        if (ans < 1 || ans > 3) {
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    private static void selectCarType(int ans) {
        stack[CarType_Q] = ans;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", ans == 1 ? "Sedan" : ans == 2 ? "SUV" : "Truck");
    }
    private static void selectEngine(int ans) {
        stack[Engine_Q] = ans;
        String name = ans == 1 ? "GM" : ans == 2 ? "TOYOTA" : ans == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private static void selectBrakeSystem(int ans) {
        stack[BrakeSystem_Q] = ans;
        String name = ans == 1 ? "MANDO" : ans == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private static void selectSteeringSystem(int ans) {
        stack[SteeringSystem_Q] = ans;
        String name = ans == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }


    private static boolean isValidCheck() {
        if (stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL) return false;
        if (stack[CarType_Q] == SUV   && stack[Engine_Q] == TOYOTA)       return false;
        if (stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA)          return false;
        if (stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO)  return false;
        if (stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S) return false;
        return true;
    }

    private static void runProducedCar() {
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

    private static void testProducedCar() {
        if (stack[CarType_Q] == SEDAN && stack[BrakeSystem_Q] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (stack[CarType_Q] == SUV && stack[Engine_Q] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (stack[CarType_Q] == TRUCK && stack[Engine_Q] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (stack[CarType_Q] == TRUCK && stack[BrakeSystem_Q] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (stack[BrakeSystem_Q] == BOSCH_B && stack[SteeringSystem_Q] != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }


    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}