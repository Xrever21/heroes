package programs;

import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import programs.utils.Point;

public class GeneratePresetImpl implements GeneratePreset {

    private static final Logger logger = Logger.getLogger(GeneratePresetImpl.class.getName());
    private static final int MAX_UNITS = 11;
    private static final int MAX_ATTEMPTS = 100;

    private static final int OPPONENT_FIELD_X = 3;
    private static final int OPPONENT_FIELD_Y = 21;

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        if (unitList == null || unitList.isEmpty() || maxPoints <= 0) {
            logger.log(Level.WARNING, "Неверный ввод: список единиц измерения пуст или максимальное количество баллов не является положительным.");
            return new Army(); // Возвращаем пустую армию
        }

        logger.log(Level.INFO, "Генерация пресета");
        logger.log(Level.INFO, "Список юнитов: {0}", unitList);
        logger.log(Level.INFO, "Максимальные очки: {0}", maxPoints);

        List<Unit> units = new ArrayList<>();
        Set<Point> occupiedPositions = new HashSet<>();

        Army computerArmy = new Army();
        int remainingPoints = maxPoints;

        unitList.sort(Comparator.comparingDouble(this::calculateEfficiency).reversed());

        for (Unit unit : unitList) {
            int count = Math.min(MAX_UNITS, remainingPoints / unit.getCost());

            for (int i = 0; i < count; i++) {
                Point position = generateUniquePositions(occupiedPositions);
                if (position == null) {
                    logger.log(Level.WARNING, "Не удалось сгенерировать уникальные позиции после {0} попыток.", MAX_ATTEMPTS);
                    break;
                }

                units.add(new Unit(unit.getName() + " " + i, unit.getUnitType(), unit.getHealth(), unit.getBaseAttack(), unit.getCost(), unit.getAttackType(), unit.getAttackBonuses(), unit.getDefenceBonuses(), position.getX(), position.getY()));
            }

            remainingPoints -= count * unit.getCost();
            if (remainingPoints <= 0) {
                break;
            }
        }

        computerArmy.setUnits(units);
        computerArmy.setPoints(maxPoints - remainingPoints);
        logger.log(Level.INFO, "Армия компьютера: {0}", computerArmy.getUnits().size());
        logger.log(Level.INFO, "Очки армии компьютера: {0}", computerArmy.getPoints());
        return computerArmy;
    }

    private double calculateEfficiency(Unit unit) {
        return (unit.getBaseAttack() / (double) unit.getCost()) + (unit.getHealth() / (double) unit.getCost());
    }

    private Point generateUniquePositions(Set<Point> occupiedPositions) {
        Random random = new Random();
        int x, y;
        Point position;
        int attempts = 0;

        do {
            x = random.nextInt(OPPONENT_FIELD_X); // Предполагается, что x может быть от 0 до 2
            y = random.nextInt(OPPONENT_FIELD_Y); // Предполагается, что y может быть от 0 до 20
            position = new Point(x, y);
            attempts++;
        } while (occupiedPositions.contains(position) && attempts < MAX_ATTEMPTS);

        if (attempts >= MAX_ATTEMPTS) {
            return null; // Не удалось найти уникальную позицию
        }

        occupiedPositions.add(position);
        return position;
    }
}
