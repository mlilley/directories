package com.mlilley.directories.commands;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import com.mlilley.directories.directories.DirectoryTree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnrecognizedCommandOutputTest {
    private DirectoryTree dt;
    private UnrecognizedCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new UnrecognizedCommand(dt, "moo");
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutputWithoutArgs() {
        cmd.execute();
        assertEquals("Command: moo\n", out.toString());
        assertEquals("Error: unrecognized command\n", err.toString());
    }

    @Test
    public void producesCorrectOutputWithArgs() {
        cmd.withArgs(Arrays.asList(new String[] {"foo", "bar"}));
        cmd.execute();
        assertEquals("Command: moo     foo     bar\n", out.toString());
        assertEquals("Error: unrecognized command\n", err.toString());
    }
}