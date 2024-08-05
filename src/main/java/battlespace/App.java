package battlespace;

import java.util.Scanner;
import battlespace.gameserver.*;

public class App {
    public static void main(String[] args) {

        BattleSpaceEngine engine = new BattleSpaceEngine();
        engine.addCommand(new FailingCommand());
        engine.processCommands(); // Запуск обработки команд

        /*
        System.out.println("Добро пожаловать в игру \"Космический бой!\"");

        Scanner scanner = new Scanner(System.in);

        // Запрос начальной позиции у пользователя
        System.out.print("Введите начальную позицию по X: ");
        int posX = scanner.nextInt();
        System.out.print("Введите начальную позицию по Y: ");
        int posY = scanner.nextInt();

        // Запрос скорости у пользователя
        System.out.print("Введите скорость скорость движения от 0 до 100: ");
        int speedM = scanner.nextInt();
        System.out.print("Введите направление движения от 0 до 359: ");
        int directM = scanner.nextInt();
        System.out.print("Введите скорость поворота от -179 до 179: ");
        int rotateS = scanner.nextInt();

        // Создание начального вектора позиции
        Vector startPosition = new Vector(posX, posY);
        // Создание начального направления движения и скорости поворота объекта - Direction
        Direction startDirection = new Direction(directM, rotateS);

        // Создание игрового объекта
        GameObject gameObject = new GameObject(startPosition, speedM, startDirection);

        // Выполнение поворота
        Rotate rotate = new Rotate(gameObject);
        rotate.Execute();

        // Выполнение перемещения
        Move move = new Move(gameObject);
        move.Execute();

        // Получение новой позиции
        Vector newPosition = gameObject.getPosition();

        // Вывод новой позиции
        System.out.println("Новая позиция объекта: x = " + newPosition.x + ", y = " + newPosition.y);
*/
    }
}

// Класс, который выполняет команду
class FailingCommand implements Command {
    private int retryCount = 0;

    @Override
    public void execute() throws Exception {
        if (retryCount < 1) { // Бросаем исключение на первой попытке
            throw new Exception("Команда не выполнена");
        }
    }

    @Override
    public int getRetryCount() {
        return retryCount;
    }

    @Override
    public void incrementRetryCount() {
        retryCount++;
    }
}

class GameObject implements Movable, Rotable {
    private Vector position;
    private Direction direction;
    private Vector velocity; // Скорость в дельта X и дельта Y
    private int speed; // Скорость от 0 до 100

    public GameObject(Vector position, int speed, Direction direction) {
        this.position = position;
        this.direction = direction;
        this.setSpeed(speed); // Инициолизирует скорость speed и velocity (вектор)
    }

    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public Vector getVelocity() {
        return this.velocity;
    }

    @Override
    public void setPosition(Vector newV) {
        this.position = newV;
    }

    @Override
    public int getDirection() {
        return this.direction.getDirection();
    }

    @Override
    public int getAngleChange() {
        return this.direction.getAngleChange();
    }

    @Override
    public void setDirection(Direction newD) {
        this.direction = newD;
        // Обновляем velocity, если направление изменилось.
        this.updateVelocity();
    }

    // Пока не используется
    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed % 100;
        // Обновляем velocity при изменении скорости.
        this.updateVelocity();
    }

    private void updateVelocity() {
        this.velocity = Vector.Convert(this.speed, this.direction.getDirection());
    }
}