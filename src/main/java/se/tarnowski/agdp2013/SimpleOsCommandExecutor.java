package se.tarnowski.agdp2013;

import java.io.File;
import java.io.IOException;

/**
 * Simple class that runs a command totally not caring about any errors or streams.
 */
public class SimpleOsCommandExecutor {

    private String workDir;
    private String command;

    public SimpleOsCommandExecutor(String workDir, String command) {
        this.workDir = workDir;
        this.command = command;
    }

    public int run() {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(workDir));
            pb.command(command);
            return pb.start().waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        SimpleOsCommandExecutor executor = new SimpleOsCommandExecutor("d:\\src\\agiledevpractices", "invoice_payments.bat");
        System.err.println(executor.run());

    }

}
