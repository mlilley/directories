package com.mlilley.directories;

import com.mlilley.directories.CommandLineManager;
import com.mlilley.directories.commands.*;
import com.mlilley.directories.directories.DirectoryTree;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandLineManagerTest {
    private CommandLineManager clm;

    @Before
    public void before() {
        clm = new CommandLineManager(new DirectoryTree());
    }

    @Test
    public void parsesCommands() {
        String[] input = {"", "dir", "up", "cd", "mkdir"};
        Class[] expected = {EmptyCommand.class, DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(0, cmd.getArgs().size());
        }
    }

    @Test
    public void parsesCommandsWithTrailingSpace() {
        String[] input = {" ", "dir ", "up ", "cd ", "mkdir "};
        Class[] expected = {EmptyCommand.class, DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(0, cmd.getArgs().size());
        }
    }

    @Test
    public void parsesCommandsWithLeadingSpace() {
        String[] input = {" ", " dir", " up", " cd", " mkdir"};
        Class[] expected = {EmptyCommand.class, DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(0, cmd.getArgs().size());
        }
    }

    @Test
    public void parsesCommandsWithLeadingAndTrailingSpace() {
        String[] input = {" ", " dir ", " up ", " cd ", " mkdir "};
        Class[] expected = {EmptyCommand.class, DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(0, cmd.getArgs().size());
        }
    }

    @Test
    public void parsesCommandsWithArgs() {
        String[] input = {"dir a b c", "up a b c", "cd a b c", "mkdir a b c"};
        Class[] expected = {DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(3, cmd.getArgs().size());
            assertEquals("a", cmd.getArgs().get(0));
            assertEquals("b", cmd.getArgs().get(1));
            assertEquals("c", cmd.getArgs().get(2));
        }
    }

    @Test
    public void parsesCommandsWithArgsWithExtraSpaces() {
        String[] input = {"dir  a  b  c  ", "up  a  b  c ", "cd  a  b  c ", "mkdir  a  b  c "};
        Class[] expected = {DirCommand.class, UpCommand.class, CdCommand.class, MkdirCommand.class};

        for (int i = 0; i < input.length; i++) {
            Command cmd = clm.parse(input[i]);
            assertTrue(expected[i].isInstance(cmd));
            assertEquals(3, cmd.getArgs().size());
            assertEquals("a", cmd.getArgs().get(0));
            assertEquals("b", cmd.getArgs().get(1));
            assertEquals("c", cmd.getArgs().get(2));
        }
    }

    @Test
    public void parsesUnrecognizedCommand() {
        Command cmd = clm.parse("moocows");
        assertTrue(cmd instanceof UnrecognizedCommand);
    }

    @Test
    public void parsesUnrecognizedCommandWithArgs() {
        Command cmd = clm.parse("moocows are brown");
        assertTrue(cmd instanceof UnrecognizedCommand);
        assertEquals(2, cmd.getArgs().size());
        assertEquals("are", cmd.getArgs().get(0));
        assertEquals("brown", cmd.getArgs().get(1));
    }
}