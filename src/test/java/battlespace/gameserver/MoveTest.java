package battlespace.gameserver;

import junit.framework.TestCase;

/**
 * Тесты для проверки класса Move.
 */
public class MoveTest extends TestCase {
    public void testMove() {
        // Тест на корректное перемещение
        MockMovable mock = new MockMovable(new Vector(12, 5), new Vector(-7, 3));
        Move move = new Move(mock);
        move.Execute();
        Vector newPosition = mock.getPosition();
        assertEquals(new Vector(5, 8), newPosition);
    }

    public void testMovePositionNull() {
        // Тест: позиция равна null
        try {
            MockMovable mock = new MockMovable(null, new Vector(-7, 3));
            Move move = new Move(mock);
            move.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (NullPointerException e) {
            // Ожидаемое исключение
        }
    }

    public void testMoveSpeedNull() {
        // Тест: скорость равна null
        try {
            MockMovable mock = new MockMovable(new Vector(12, 5), null);
            Move move = new Move(mock);
            move.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (NullPointerException e) {
            // Ожидаемое исключение
        }
    }

    public void testMoveCannotSetPosition() {
        // Тест: невозможно изменить позицию
        try {
            MockMovableUnmodifiable mock = new MockMovableUnmodifiable(new Vector(12, 5), new Vector(-7, 3));
            Move move = new Move(mock);
            move.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (UnsupportedOperationException e) {
            // Ожидаемое исключение
        }
    }
}

/**
 * Mock-класс для тестов, реализующий интерфейс Movable.
 */
class MockMovable implements Movable {
    private Vector position;
    private final Vector velocity;

    public MockMovable(Vector position, Vector velocity) {
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

/**
 * Mock-класс для тестов, который не позволяет изменить позицию.
 */
class MockMovableUnmodifiable extends MockMovable {
    public MockMovableUnmodifiable(Vector position, Vector velocity) {
        super(position, velocity);
    }

    @Override
    public void setPosition(Vector newV) {
        throw new UnsupportedOperationException("Невозможно изменить позицию");
    }
}

