package battlespace.gameserver;

/**
 * Интерфейс для объектов, которые могут поворачивать.
 */
public interface Rotable {
    int getDirection(); // Получить текущее значение направления движения.
    int getAngleChange(); // Получить текущую скорость поворота.
    void setDirection(Direction direction); // Установить новые значения направления движения и скорости угла поворота.
}
