package com.mlilley.directories.commands;

import com.mlilley.directories.directories.DirectoryTree;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DirCommandOutputTest {
    private DirectoryTree dt;
    private DirCommand cmd;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @Before
    public void before() {
        dt = new DirectoryTree();
        cmd = new DirCommand(dt);
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        cmd.withOutputStreams(out, err);
    }

    @Test
    public void producesCorrectOutputInRootWithNoSubdirs() {
        cmd.execute();
        assertEquals(
                "Command: dir\n" +
                "Directory of root:\n" +
                "No subdirectories\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void producesCorrectOutputInRootWithSubdirs() throws Exception {
        dt.createSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.createSubdirectory("baz");
        cmd.execute();
        assertEquals(
                "Command: dir\n" +
                "Directory of root:\n" +
                "bar     baz     foo\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void producesCorrectOutputInSubdirWithNoSubdirs() throws Exception {
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        cmd.execute();
        assertEquals(
                "Command: dir\n" +
                        "Directory of root\\foo:\n" +
                        "No subdirectories\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void producesCorrectOutputInSubdirWithSubdirs() throws Exception {
        dt.createSubdirectory("foo");
        dt.navigateToSubdirectory("foo");
        dt.createSubdirectory("bar");
        dt.createSubdirectory("baz");
        cmd.execute();
        assertEquals(
                "Command: dir\n" +
                        "Directory of root\\foo:\n" +
                        "bar     baz\n", out.toString());
        assertEquals("", err.toString());
    }

    @Test
    public void producesCorrectOutputWithArgs() {
        cmd.withArgs(Arrays.asList(new String[] {"foo", "bar"}));
        cmd.execute();
        assertEquals("Command: dir     foo     bar\n", out.toString());
        assertEquals("Error: dir takes no arguments\n", err.toString());
    }
}