package com.smartphonedev.simulator;

import com.smartphonedev.site.Site;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class SimulationBootStrapper
{
    @Getter
    private Optional<Site> site;

    public SimulationBootStrapper(String[] args)
    {
        site = Optional.empty();
        if((args != null) &&(args.length == 1))
        {
            var siteFile = new File(args[0]);
            try
            {
                var inputStream = new FileInputStream(siteFile);
                startSimulation(inputStream);

            }catch (FileNotFoundException fnf)
            {
                System.out.println("Sitemap file was not found");
            }
        }
    }

    public void startSimulation(InputStream input)  {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            var inputList = new ArrayList<String>();
            Integer lineLength = null; //used Integer instead of int so I have a null value
            String line;
            while((line = reader.readLine()) != null)
            {
                if(lineLength == null)
                {
                    lineLength = line.length();
                    inputList.add(line);
                }else
                {
                    if(lineLength.compareTo(line.length()) == 0)
                    {
                        inputList.add(line);
                    }else
                    {
                        System.out.println("Sitemap file has inconsistent line lengths");
                        return;
                    }
                }
            }

            site = Site.configureSite(inputList);

        } catch (IOException uee)
        {
            //site will be empty
            System.out.println("Sitemap could not be loaded from the input provided");
        }
    }
}
