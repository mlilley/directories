package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MkdirCommandOutputTest {
    private DirectoryTree dt;
    private MkdirCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new MkdirCommand(dt);
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutputWithNoArgs() {
        cmd.execute();
        assertEquals("Command: mkdir\n", out.toString());
        assertEquals("Error: mkdir takes 1 argument\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithTooManyArgs() {
        cmd.withArgs(Arrays.asList(new String[] {"foo", "bar"}));
        cmd.execute();
        assertEquals("Command: mkdir   foo     bar\n", out.toString());
        assertEquals("Error: mkdir takes 1 argument\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithExistingDir() throws Exception {
        dt.createSubdirectory("foo");
        cmd.withArgs(Arrays.asList(new String[] {"foo"}));
        cmd.execute();
        assertEquals("Command: mkdir   foo\n", out.toString());
        assertEquals("Subdirectory already exists\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithNonExistingDir() throws Exception {
        cmd.withArgs(Arrays.asList(new String[] {"foo"}));
        cmd.execute();
        assertEquals("Command: mkdir   foo\n", out.toString());
        assertEquals("", err.toString());
    }
}
