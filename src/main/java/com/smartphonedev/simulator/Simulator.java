package com.smartphonedev.simulator;

import com.smartphonedev.bulldozer.Command;
import com.smartphonedev.exceptions.InvalidMapException;
import com.smartphonedev.exceptions.MissingMapException;
import com.smartphonedev.exceptions.SimulationException;
import com.smartphonedev.site.Site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulator
{
    private static final String EXIT_COMMAND = "exit";
    public static void main(String[] args)
    {
        var bootStrapper = new SimulationBootStrapper(args);

        System.out.println("Welcome to the Aconex site clearing simulator.");
        Site.getInstance().ifPresentOrElse(
                site -> {
                    System.out.println("This is a map of the site: ");
                    site.printSiteMap();
                },
                () -> {
                    System.out.print("No valid map provided");
                    System.exit(0);
                }
        );

        System.out.print("robot:>");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            boolean willContinue = true;
            Command command = null;
            while(willContinue)
            {
                var commandString = reader.readLine();
                if(commandString.compareToIgnoreCase(EXIT_COMMAND) != 0)
                {

                }else
                {
                    willContinue = false;
                }
                System.out.print("simulation:>");
            }
        }catch (IOException ioe)
        {
            System.out.println("There was an error, ToyRobot is giving up!");
        }
    }
}
