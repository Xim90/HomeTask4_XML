package by.it_academy.entity;

import java.util.Objects;

public class Hotkeys {
    private String hotkey;

    public Hotkeys() {
    }

    public Hotkeys(String hotkey) {
        this.hotkey = hotkey;
    }

    public String getHotkey() {
        return hotkey;
    }

    public void setHotkey(String hotkey) {
        this.hotkey = hotkey;
    }

    @Override
    public String toString() {
        return "\t\t\t" + hotkey + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotkeys hotkeys)) return false;

        return Objects.equals(hotkey, hotkeys.hotkey);
    }

    @Override
    public int hashCode() {
        return hotkey != null ? hotkey.hashCode() : 0;
    }
}
