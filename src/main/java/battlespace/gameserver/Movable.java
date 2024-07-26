package battlespace.gameserver;

/**
 * Интерфейс для объектов, которые могут двигаться.
 */
public interface Movable {
    Vector getPosition();   // Получить текущую позицию
    Vector getVelocity();   // Получить текущую скорость
    void setPosition(Vector newV);  // Установить новую позицию
}
