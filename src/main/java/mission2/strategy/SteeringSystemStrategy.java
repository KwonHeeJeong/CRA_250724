package mission2.strategy;

import mission2.Assemble;

public class SteeringSystemStrategy implements AssembleStrategy {
    @Override
    public void selectSystem(int answer) {
        selectSteeringSystem(answer);
    }
    public void showMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    public boolean isValidRange(int ans) {
        if (ans < 0 || ans > 2) {
            System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
            return false;
        }
        return true;
    }

    public void selectSteeringSystem(int ans) {
        String name = ans == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }
}