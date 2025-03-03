# Heroes III Battle Simulator

Проект представляет собой реализацию симулятора боевой системы в стиле игры "Герои Меча и Магии III". Основная задача - разработка стратегии боя против искусственного интеллекта.

## 🎮 Функциональность

Проект реализует четыре ключевых метода для симуляции боя:

1. **Поиск подходящих юнитов для атаки** (`getSuitableUnits`)
   - Определяет юниты, которые могут атаковать в текущий момент
   - Учитывает расположение юнитов на поле боя и их характеристики

2. **Генерация пресета армии** (`generate`)
   - Создает сбалансированную армию с учетом доступных ресурсов
   - Оптимизирует состав армии для эффективного боя

3. **Поиск пути к цели** (`getTargetPath`)
   - Находит оптимальный путь для перемещения юнита к цели
   - Учитывает препятствия и других юнитов на поле боя

4. **Симуляция боя** (`simulate`)
   - Проводит симуляцию сражения между двумя армиями
   - Определяет результат боя и потери сторон

## 🛠 Технологии

- Java
- JUnit (для тестирования)

## 📁 Структура проекта 
heroes/
├── src/
│ ├── main/
│ │ └── java/
│ │ ├── interfaces/
│ │ │ ├── SuitableForAttackUnitsFinder.java
│ │ │ ├── GeneratePreset.java
│ │ │ ├── UnitTargetPathFinder.java
│ │ │ └── SimulateBattle.java
│ │ └── implementation/
│ │ ├── SuitableForAttackUnitsFinderImpl.java
│ │ ├── GeneratePresetImpl.java
│ │ ├── UnitTargetPathFinderImpl.java
│ │ └── SimulateBattleImpl.java
│ └── test/
│ └── java/
│ └── implementation/
│ ├── SuitableForAttackUnitsFinderImplTest.java
│ ├── GeneratePresetImplTest.java
│ ├── UnitTargetPathFinderImplTest.java
│ └── SimulateBattleImplTest.java
└── resources/
