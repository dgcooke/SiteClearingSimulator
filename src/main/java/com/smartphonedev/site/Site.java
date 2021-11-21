package com.smartphonedev.site;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public final class Site
{
    private final static int ARRAY_START = 0;
    private Terrain[][] site;
    private Optional<Position> position;
    private static Site instance;

    public static Optional<Site> configureSite(List<String> siteContents)
    {
        if((siteContents == null) || (siteContents.isEmpty()))
        {
            return Optional.empty();
        }

        if(instance == null)
        {
            instance = new Site();
        }

        instance.site = new Terrain[siteContents.size()][siteContents.get(0).length()];

        var rowIterator = siteContents.listIterator();
        while (rowIterator.hasNext())
        {
            var rowIndex = rowIterator.nextIndex();
            var row = rowIterator.next();

            row.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).listIterator();
            var columnIterator = row.chars()
                    .mapToObj(c -> (char) c).collect(Collectors.toList())
                    .listIterator();

            while (columnIterator.hasNext())
            {
                var columnIndex = columnIterator.nextIndex();
                var columnItem = columnIterator.next();
                instance.site[rowIndex][columnIndex] = (columnItem == 'o' ) ? new PlainTerrain(): new RockyTerrain();
            }
        }

        return Optional.of(instance);
    }

    public static Optional<Site> getInstance()
    {
        if(instance != null)
        {
            return Optional.of(instance);
        }
        return Optional.empty();
    }

    public void printSiteMap()
    {
        if(instance != null)
        {
            var output = new String[site.length][site[ARRAY_START].length];
            var rowPosition = 0;
            var columnPosition = 0;

            while(rowPosition < site.length)
            {
                Terrain[] siteRow = site[rowPosition];
                for(var terrain: siteRow)
                {
                    System.out.print(terrain.print());
                    columnPosition++;
                }
                System.out.print("\n");
                rowPosition++;
            }
        }
    }

}
