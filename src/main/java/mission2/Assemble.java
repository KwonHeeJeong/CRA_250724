package mission2;

import mission2.strategy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static mission2.Constant.*;

public class Assemble {
    private static int[] stack = new int[5];
    public static int[] getStack() {
        return stack;
    }

    private final Map<Integer, AssembleStrategy> strategies = new HashMap<>();

    public Assemble() {
        strategies.put(CarType_Q, new CarTypeStrategy());
        strategies.put(Engine_Q, new EngineStrategy());
        strategies.put(BrakeSystem_Q, new BrakeSystemStrategy());
        strategies.put(SteeringSystem_Q, new SteeringSystemStrategy());
        strategies.put(Run_Test, new RunTestStrategy());
    }

    protected void doAssemble() {
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

    protected void doActionOfCurrentStep(int currentStep, int answer) {
        AssembleStrategy strategy = strategies.get(currentStep);
        if (strategy != null) {
            strategy.selectSystem(answer);
            delay(800);
            if(currentStep != Run_Test)
                stack[currentStep] = answer;
        }
    }

    protected int backStep(int currentStep) {
        if (currentStep == Run_Test) {
            currentStep = CarType_Q;
        }
        else if (currentStep > CarType_Q) {
            currentStep--;
        }
        return currentStep;
    }

    protected int nextStep(int currentStep) {
        if (currentStep < Run_Test) {
            currentStep++;
        }
        return currentStep;
    }

    protected boolean isInvalidInput(String buf, int currentStep) {
        if (isInvalidInputFormat(buf)) return true;
        if (isInvalidInputRange(currentStep, buf)) return true;
        return false;
    }

    protected boolean isInvalidInputRange(int currentStep, String buf) {
        if (!isValidRange(currentStep, Integer.parseInt(buf))) {
            delay(800);
            return true;
        }
        return false;
    }

    protected void startCurrentStep(int currentStep) {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
        showMenuForUser(currentStep);
    }

    protected void showMenuForUser(int currentStep) {
        AssembleStrategy strategy = strategies.get(currentStep);
        if (strategy != null) {
            strategy.showMenu();
        }
    }

    protected boolean isInvalidInputFormat(String buf) {
        if (!buf.matches("[0-9]+")) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return true;
        }
        return false;
    }

    protected String getInputBuffer(Scanner sc) {
        System.out.print("INPUT > ");
        return sc.nextLine().trim();
    }

    protected boolean isExitInput(String buf) {
        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return true;
        }
        return false;
    }

    protected boolean isValidRange(int step, int ans) {
        AssembleStrategy strategy = strategies.get(step);
        if (strategy != null) {
            return strategy.isValidRange(ans);
        }
        return false;
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

}