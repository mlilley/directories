package com.mlilley.directories;

import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SampleTest {

    @Test
    public void producesCorrectOutputForProg5SampleRun() throws Exception {
        String sampleOutput = getResourceAsString("prog5output.dat");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outPrint = new PrintStream(out);

        System.setIn(getClass().getResourceAsStream("prog5.dat"));
        System.setOut(outPrint);
        System.setErr(outPrint);

        App.main(new String[] {});

        String actualOutput = out.toString();
        assertEquals(sampleOutput, actualOutput);
    }

    private String getResourceAsString(String filename) {
        Scanner s = new Scanner(getClass().getResourceAsStream(filename));
        s.useDelimiter("\\A");
        String content = s.next();
        s.close();
        return content;
    }
}