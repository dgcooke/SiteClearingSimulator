package com.smartphonedev.simulator;

import com.smartphonedev.bulldozer.Bulldozer;
import com.smartphonedev.bulldozer.Command;
import com.smartphonedev.site.Site;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class Simulator
{

    private static final String EXIT_COMMAND = "quit";
    private static final String SHORT_EXIT_COMMAND = "q";

    public static void main(String[] args)
    {
        var bootStrapper = new SimulationBootStrapper(args);
        var bulldozer = new Bulldozer();
        Site.getInstance().ifPresentOrElse(
                site -> {
                    System.out.println("This is a map of the site:\n");
                    site.printSiteMap();
                },
                () -> {
                    System.out.print("No valid map provided");
                    System.exit(0);
                }
        );
        System.out.println("\nThe bulldozer is currently located at the Northern edge of the");
        System.out.println("site, immediately to the West of the site, and facing East.\n");

        System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            boolean willContinue = true;
            Optional<Command> command = Optional.empty();
            while(willContinue)
            {
                var commandString = reader.readLine();
                if(commandString.compareToIgnoreCase(EXIT_COMMAND) != 0)
                {
                    command = Command.parseCommand(commandString);
                    if(!bulldozer.processCommand(command))
                    {
                        exitSimulation(bulldozer);
                    }
                }else
                {
                    willContinue = false;
                }
                System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit:");
            }
        }catch (IOException ioe)
        {
            System.out.println("There was an error, simulator is giving up!");
        }
    }

    private static void exitSimulation(Bulldozer bulldozer)
    {

        System.out.println("The simulation has ended at your request. These are the commands\n you issued:");
        if(bulldozer != null)
        {
            bulldozer.printCommands();
        }
        var site = Site.getInstance();
        System.out.println("Total fuel usage: "+site.map(s-> s.calculateFuelUsaga()).orElseGet(() -> 0));
        System.out.println("\nThank you for using the Aconex site clearing simulator.");
        System.exit(0);
    }
}
