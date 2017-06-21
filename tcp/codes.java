package tcp;


// Encode format : action + keystatus
public class codes {
    // actions
    public static final int MOVELEFT = 0x0;
    public static final int MOVERIGHT = 0x1;
    public static final int MOVEUP = 0x2;
    public static final int MOVEDOWN = 0x3;
    public static final int ATTACK = 0x4;
    // keystatus
    public static final int KEYDOWN = 0x0;
    public static final int KEYRELEASE = 0x1;
    private codes() {};
}
