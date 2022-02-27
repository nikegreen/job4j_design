package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertThat(config.value("surname"), is(""));
    }

    @Test(expected = NullPointerException.class)
    public void whenPairWithoutCommentValueNull() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertThat(config.value("surname"), is(""));
        assertThat(config.value("illegalKey"), is(""));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("nikegreen"));
        assertThat(config.value("surname"), is(""));
    }

    @Test
    public void whenPairWithNoKey() {
        String path = "./data/pair_with_illegal_no_key.properties";
        Config config = new Config(path);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Отсутствует ключ");
        config.load();
    }

    @Test
    public void whenPairWithDoubleEqual() {
        String path = "./data/pair_with_illegal_double_equal.properties";
        Config config = new Config(path);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Повторяется \"=\"");
        config.load();
    }

    @Test
    public void whenPairWithThereIsNoEqual() {
        String path = "./data/pair_with_illegal_there_is_no_equal.properties";
        Config config = new Config(path);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Отсутствует \"=\"");
        config.load();
    }
}
