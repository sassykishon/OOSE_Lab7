package game.card.game.TwentyOne;
import java.util.HashMap;
import java.util.Map;

public enum TwentyOneActions {
    TWIST("Twist"),
    STICK("Stick");

    private String fullName;
    private static final Map<String, TwentyOneActions> lookup = new HashMap<String, TwentyOneActions>();

    TwentyOneActions(String fullName) {
        this.fullName = fullName;
    }

    static {
        for (TwentyOneActions action : TwentyOneActions.values()) {
            lookup.put(action.fullName.substring(0, 1), action);
        }
    }

    public String display() {
        return fullName;
    }

    public static TwentyOneActions getAction(String shortCode) {
        return lookup.get(shortCode);
    }

}
