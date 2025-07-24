package mission2.strategy;

import mission2.Assemble;

public interface AssembleStrategy {
    void selectSystem(int answer);
    void showMenu();
    boolean isValidRange(int ans);
}