package bredesh.actors;

public class LocationActor {
    private final int initial_x;
    private final int initial_y;

    private int x;
    private int y;
    private int width;
    private int height;

    public LocationActor(int x, int y, int width, int height) {
        this.initial_x = this.x = x;
        this.initial_y = this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    private int getWidth() {
        return width;
    }

    private int getHeight() {
        return height;
    }

    boolean isTouching(LocationActor other) {
        return (other.getX() < x && x < other.getX() + other.getWidth())
                && (other.getY() > y && y > other.getY() - 20);
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }

    public void moveDown() {
        y++;
    }

    public boolean isOutOfFrame() {
        return getX() < -200;
    }

    public void resetHorizontal(int x) {
        this.x = x;
    }

    void resetToInitial() {
        this.x = initial_x;
        this.y = initial_y;
    }

}
