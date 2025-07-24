package mission2.strategy;

import mission2.Assemble;

public class EngineStrategy implements AssembleStrategy {
    @Override
    public void selectSystem(int answer) {
        selectEngine(answer);
    }
    public void showMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }
    public boolean isValidRange(int ans) {
        if (ans < 0 || ans > 4) {
            System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
            return false;
        }
        return true;
    }


    protected void selectEngine(int ans) {
        String name = ans == 1 ? "GM" : ans == 2 ? "TOYOTA" : ans == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
}