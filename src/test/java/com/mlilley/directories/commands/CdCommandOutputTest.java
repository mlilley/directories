package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CdCommandOutputTest {
    private DirectoryTree dt;
    private CdCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new CdCommand(dt);
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutputWithNoArgs() {
        cmd.execute();
        assertEquals("Command: cd\n", out.toString());
        assertEquals("Error: cd takes 1 argument\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithTooManyArgs() {
        cmd.withArgs(Arrays.asList(new String[] {"foo", "bar"}));
        cmd.execute();
        assertEquals("Command: cd      foo     bar\n", out.toString());
        assertEquals("Error: cd takes 1 argument\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithInvalidDir() {
        cmd.withArgs(Arrays.asList(new String[] {"foo"}));
        cmd.execute();
        assertEquals("Command: cd      foo\n", out.toString());
        assertEquals("Subdirectory does not exist\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithValidDir() throws Exception {
        dt.createSubdirectory("foo");
        cmd.withArgs(Arrays.asList(new String[] {"foo"}));
        cmd.execute();
        assertEquals("Command: cd      foo\n", out.toString());
        assertEquals("", err.toString());
    }
}
