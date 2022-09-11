package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseNull() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Cannot read the array length because");
    }

    @Test
    void checkParseEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] s = {};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkValidateSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] s = {""};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: ")
                .hasMessageContaining(" does not contain the symbol \"=\"");
    }

    @Test
    void checkValidateFirstSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] s = {"=ryu4uw"};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: ")
                .hasMessageContaining(" does not contain a key");
    }

    @Test
    void checkValidateLastSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] s = {"rxj="};
        assertThatThrownBy(() -> nameLoad.parse(s))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: ")
                .hasMessageContaining(" does not contain a value");
    }
}
