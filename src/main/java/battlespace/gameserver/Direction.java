package battlespace.gameserver;

/**
 * Представляет нарправление движения в градусах от 0 до 360.
 */
public class Direction {
    private int d;  // Угол направления движения, направление 0-359 градусов
    private int a;  // Скорость изменения угла направления движения -179 - 170 градусов

    public Direction(int d, int a) {
        this.d = ((d % 360) + 360) % 360;  // Приведение к диапазону 0-359
        this.a = a % 180;  // Приведение к диапазону -179 - 179 с сохранением направления
        if ((a < 0 && this.a > 0) || (a > 0 && this.a < 0)) {
            this.a = -this.a;  // Сохранение знака изменения направления
        }
    }

    // Метод для вычисления нового направления движения
    public static Direction Rotate(int d, int a) {
        int nd = (d + a) % 360;
        if (nd < 0) {
            nd += 360;
        }
        return new Direction(nd, a); // Возвращение нового направления движения.
    }

    public int getDirection() {
        return d;
    }

    public int getAngleChange() {
        return a;
    }
}