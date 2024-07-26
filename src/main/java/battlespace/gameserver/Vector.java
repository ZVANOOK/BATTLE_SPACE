package battlespace.gameserver;

/**
 * Представляет двумерный вектор со статическим методом для сложения векторов.
 */
public class Vector {
    public final int x;  // Координата x
    public final int y;  // Координата y

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Складывает два вектора и возвращает новый вектор.
     */
    public static Vector Plus(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;
        return x == vector.x && y == vector.y;
    }
}
