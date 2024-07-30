package battlespace.gameserver;

/**
 * Интерфейс для объектов, которые могут поворачивать.
 */
public interface Rotable {
    int getRotation(); // Получить текущее значение угла поворота -360-360
    Vector getNewVelocity(Vector velocity); // Получить новую скорость по направлению
    void setRotation(int rotation); // Установить новое значение угла поворота -360-360.(int diraction);
}
