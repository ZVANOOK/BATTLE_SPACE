package battlespace;

import java.util.Scanner;
import battlespace.gameserver.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру \"Космический бой!\"");

        Scanner scanner = new Scanner(System.in);

        // Запрос начальной позиции у пользователя
        System.out.print("Введите начальную позицию по X: ");
        int posX = scanner.nextInt();
        System.out.print("Введите начальную позицию по Y: ");
        int posY = scanner.nextInt();

        // Запрос скорости у пользователя
        System.out.print("Введите скорость по X: ");
        int velX = scanner.nextInt();
        System.out.print("Введите скорость по Y: ");
        int velY = scanner.nextInt();

        // Создание начального вектора позиции и скорости
        Vector startPosition = new Vector(posX, posY);
        Vector velocity = new Vector(velX, velY);

        // Создание игрового объекта
        GameObject gameObject = new GameObject(startPosition, velocity);

        // Выполнение перемещения
        Move move = new Move(gameObject);
        move.Execute();

        // Получение новой позиции
        Vector newPosition = gameObject.getPosition();

        // Вывод новой позиции
        System.out.println("Новая позиция объекта: x = " + newPosition.x + ", y = " + newPosition.y);
    }
}

// Класс игрового объекта, реализующий интерфейс Movable
class GameObject implements Movable {
    private Vector position;
    private final Vector velocity;

    public GameObject(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
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
}