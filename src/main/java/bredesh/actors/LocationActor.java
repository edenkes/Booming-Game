package bredesh.actors;

import static bredesh.GUI.GUIConstants.FRAME_WIGHT;

public class LocationActor {
    public static final int OUT_OF_FRAME_RIGHT = FRAME_WIGHT - 100;
    public static final int OUT_OF_FRAME_LEFT = -190;
    public static final int PADDING_HORIZONTAL = 5;
    public static final int PADDING_VERTICAL = 10;

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
        return isHorizontalTouching(other)
                && isVerticalTouching(other);
    }

    private boolean isHorizontalTouching(LocationActor other) {
        return ((x - PADDING_HORIZONTAL < other.x && other.x < x + width - PADDING_HORIZONTAL)
                || (x - PADDING_HORIZONTAL < other.x + other.width && other.x + other.width < x + width - PADDING_HORIZONTAL));
    }

    private boolean isVerticalTouching(LocationActor other) {
        return ((y - PADDING_VERTICAL < other.y && other.y < y)
                || (y - PADDING_VERTICAL < other.y - other.height && other.y - other.height < y));
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

    public boolean isOutOfRightFrame() {
        return OUT_OF_FRAME_RIGHT < x;
    }

    public boolean isOutOfLeftFrame() {
        return x < OUT_OF_FRAME_LEFT;
    }

    public void resetHorizontal(int x) {
        this.x = x;
    }

    public void resetToInitial() {
        this.x = initial_x;
        this.y = initial_y;
    }

}
