package programs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

public class SimulateBattleImpl implements SimulateBattle {
    private PrintBattleLog printBattleLog;
    private static final Logger logger = Logger.getLogger(GeneratePresetImpl.class.getName());

    @Override
    public void simulate(Army playerArmy, Army computerArmy) throws InterruptedException {
        List<Unit> playerUnits = playerArmy.getUnits();
        List<Unit> computerUnits = computerArmy.getUnits();

        List<Unit> allUnits = new ArrayList<>();
        allUnits.addAll(playerUnits);
        allUnits.addAll(computerUnits);

        allUnits.sort(Comparator.comparingInt(Unit::getBaseAttack).reversed());

        while (hasAliveUnits(playerUnits) && hasAliveUnits(computerUnits)) {
            for (Unit unit : allUnits) {
                if (!unit.isAlive()) continue;
                logger.log(Level.INFO, "{0} атакует", unit.getName());
                Unit target = unit.getProgram().attack();
                printBattleLog.printBattleLog(unit, target);
            }
        }

        logger.log(Level.INFO, "Битва закончена");
    }

    private boolean hasAliveUnits(List<Unit> units) {
        return units.stream().anyMatch(Unit::isAlive);
    }
}
