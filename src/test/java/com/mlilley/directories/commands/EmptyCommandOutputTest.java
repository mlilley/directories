package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmptyCommandOutputTest {
    private DirectoryTree dt;
    private EmptyCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new EmptyCommand(dt);
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutput() {
        cmd.execute();
        assertEquals("", out.toString());
        assertEquals("", err.toString());
    }
}