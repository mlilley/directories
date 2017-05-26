package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpCommandOutputTest {
    private DirectoryTree dt;
    private UpCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new UpCommand(dt);
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutputInRoot() {
        cmd.execute();
        assertEquals("Command: up\n", out.toString());
        assertEquals("Cannot move up from root directory\n", err.toString());
    }

    @Test
    public void producesCorrectOutputInSubdir() throws Exception {
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        cmd.execute();
        assertEquals("Command: up\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void producesCorrectOutputWithArgs() {
        cmd.withArgs(Arrays.asList(new String[] {"foo", "bar"}));
        cmd.execute();
        assertEquals("Command: up      foo     bar\n", out.toString());
        assertEquals("Error: up takes no arguments\n", err.toString());
    }
}