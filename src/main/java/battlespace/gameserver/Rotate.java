package battlespace.gameserver;

/**
 * Класс, который выполняет поворот вектора скорости объектов.
 */
public class Rotate {
    Rotable r;

    public Rotate(Rotable r) {
        this.r = r;
    }
    /**
     * Выполняет поворот: текущее направление + угол поворота (по модулю 360).
     */
    public void Execute() {
        r.setDirection(Direction.Rotate(
                r.getDirection(),
                r.getAngleChange()
        ));
    }
}
