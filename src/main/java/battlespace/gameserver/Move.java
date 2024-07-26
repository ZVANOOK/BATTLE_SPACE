package battlespace.gameserver;

/**
 * Класс, который выполняет перемещение объектов.
 */
public class Move {
    Movable m;

    public Move(Movable m) {
        this.m = m;
    }

    /**
     * Выполняет перемещение: позиция = позиция + скорость.
     */
    public void Execute() {
        m.setPosition(Vector.Plus(
                m.getPosition(),
                m.getVelocity()
        ));
    }
}
