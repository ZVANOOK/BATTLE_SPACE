package battlespace.gameserver;

import junit.framework.TestCase;

/**
 * Тесты для проверки класса Rotate.
 */
public class RotateTest extends TestCase {

    public void testExecuteSimpleRotation() {
        // Тест на простой поворот вектора
        MockRotable mock = new MockRotable(90, 45); // Начальное направление 90, изменение угла 45
        Rotate rotate = new Rotate(mock);
        rotate.Execute();
        assertEquals(135, mock.getDirection()); // Ожидаем, что новое направление равно 135
    }

    public void testExecuteRotationWithWrapAround() {
        // Тест на поворот вектора с обёрткой через 360 градусов
        MockRotable mock = new MockRotable(350, 20); // Начальное направление 350, изменение угла 20
        Rotate rotate = new Rotate(mock);
        rotate.Execute();
        assertEquals(10, mock.getDirection()); // Ожидаем, что новое направление равно 10
    }

    public void testExecuteNegativeRotation() {
        // Тест на поворот вектора в отрицательную сторону
        MockRotable mock = new MockRotable(10, -30); // Начальное направление 10, изменение угла -30
        Rotate rotate = new Rotate(mock);
        rotate.Execute();
        assertEquals(340, mock.getDirection()); // Ожидаем, что новое направление равно 340
    }

    public void testRotationDirectionNull() {
        // Тест: направление равно null
        try {
            MockRotableInvalid mock = new MockRotableInvalid(null, 45);
            Rotate rotate = new Rotate(mock);
            rotate.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (NullPointerException e) {
            // Ожидаемое исключение
        }
    }

    public void testRotationAngleChangeNull() {
        // Тест: изменение угла движения равно null
        try {
            MockRotableInvalid mock = new MockRotableInvalid(90, null);
            Rotate rotate = new Rotate(mock);
            rotate.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (NullPointerException e) {
            // Ожидаемое исключение
        }
    }

    public void testRotationCannotSetDirection() {
        // Тест: невозможно изменить направление
        try {
            MockRotableUnmodifiable mock = new MockRotableUnmodifiable(90, 45);
            Rotate rotate = new Rotate(mock);
            rotate.Execute();
            fail("Должно было быть выброшено исключение");
        } catch (UnsupportedOperationException e) {
            // Ожидаемое исключение
        }
    }
}

/**
 * Mock-класс для тестов, реализующий интерфейс Rotable.
 */
class MockRotable implements Rotable {
    private int direction;
    private final int angleChange;

    public MockRotable(Integer direction, Integer angleChange) {
        if (direction == null || angleChange == null) {
            throw new NullPointerException();
        }
        this.direction = direction;
        this.angleChange = angleChange;
    }

    @Override
    public int getDirection() {
        return this.direction;
    }

    @Override
    public int getAngleChange() {
        return this.angleChange;
    }

    @Override
    public void setDirection(Direction newDirection) {
        this.direction = newDirection.getDirection();
    }
}

/**
 * Mock-класс для тестов с невозможностью изменения направления.
 */
class MockRotableUnmodifiable extends MockRotable {
    public MockRotableUnmodifiable(int direction, int angleChange) {
        super(direction, angleChange);
    }

    @Override
    public void setDirection(Direction newDirection) {
        throw new UnsupportedOperationException("Невозможно изменить направление");
    }
}

/**
 * Mock-класс для проверка на несоответствующие значения direction или angleChange.
 */
class MockRotableInvalid implements Rotable {
    private Integer direction;
    private Integer angleChange;

    public MockRotableInvalid(Integer direction, Integer angleChange) {
        this.direction = direction;
        this.angleChange = angleChange;
    }

    @Override
    public int getDirection() {
        if (direction == null) {
            throw new NullPointerException();
        }
        return direction;
    }

    @Override
    public int getAngleChange() {
        if (angleChange == null) {
            throw new NullPointerException();
        }
        return angleChange;
    }

    @Override
    public void setDirection(Direction newDirection) {
        if (newDirection == null) {
            throw new NullPointerException();
        }
        this.direction = newDirection.getDirection();
    }
}
