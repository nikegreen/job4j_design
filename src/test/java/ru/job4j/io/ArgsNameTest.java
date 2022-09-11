package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request")).isEqualTo("?msg=Exit=");
    }

    @Test
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {});
        assertThatThrownBy(() -> jvm.get("Xmx"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{"-enconding=UTF-8", "-Xmx="}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}