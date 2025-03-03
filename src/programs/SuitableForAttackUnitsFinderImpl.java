package programs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitableUnits = new ArrayList<>();
        Set<String> aliveUnitPositions = new HashSet<>();

        for (List<Unit> row : unitsByRow) {
            for (Unit unit : row) {
                if (unit != null && unit.isAlive()) {
                    aliveUnitPositions.add(getPositionKey(unit.getxCoordinate(), unit.getyCoordinate()));
                }
            }
        }

        for (List<Unit> row : unitsByRow) {
            for (Unit currentUnit : row) {
                if (currentUnit != null && currentUnit.isAlive()) {
                    int x = currentUnit.getxCoordinate();
                    int y = currentUnit.getyCoordinate();

                    if (isLeftArmyTarget) {
                        if (y == 0 || !aliveUnitPositions.contains(getPositionKey(x, y - 1))) {
                            suitableUnits.add(currentUnit);
                        }
                    } else {
                        if (y == row.size() - 1 || !aliveUnitPositions.contains(getPositionKey(x, y + 1))) {
                            suitableUnits.add(currentUnit);
                        }
                    }
                }
            }
        }

        if (suitableUnits.isEmpty()) {
            System.out.println("Подходящие юниты не найдены");
        }
        return suitableUnits;
    }

    private String getPositionKey(int x, int y) {
        return x + "," + y;
    }
}
