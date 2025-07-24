package mission2.strategy;

public class BrakeSystemStrategy implements AssembleStrategy {
    @Override
    public void selectSystem(int answer) {
        selectBrakeSystem(answer);
    }
    public void showMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }
    public boolean isValidRange(int ans) {
        if (ans < 0 || ans > 3) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }

    public void selectBrakeSystem(int ans) {
        String name = ans == 1 ? "MANDO" : ans == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
}