package mission2.strategy;

import mission2.Assemble;

public class CarTypeStrategy implements AssembleStrategy {
    @Override
    public void selectSystem(int answer) {
        selectCarType(answer);
    }

    public void showMenu() {
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

    public boolean isValidRange(int ans) {
        if (ans < 1 || ans > 3) {
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    protected void selectCarType(int ans) {
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", ans == 1 ? "Sedan" : ans == 2 ? "SUV" : "Truck");
    }
}