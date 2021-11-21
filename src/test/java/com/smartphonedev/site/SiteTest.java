package com.smartphonedev.site;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SiteTest
{

    @Test
    protected void configureSiteReturnsEmptySiteWhenPassedNullAsSiteList()
    {
        //given
        //when
        var result = Site.configureSite(null);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    protected void configureSiteReturnsEmptySiteWhenPassedEmptySiteList()
    {
        //given
        //when
        var result = Site.configureSite(new ArrayList<>());
        //then
        assertThat(result).isEmpty();
    }


    @Test
    protected void configureSiteReturnsSiteWhenPassedValidSiteList()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");

        //when
        var result = Site.configureSite(siteList);

        //then
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isInstanceOf(Site.class);
    }

    @Test
    protected void configureSiteReturnsCorrectTerrainObjectsWhenPassedValidSiteList()
    {
        //given
        var siteList = Arrays.asList("ooorrrrooo", "ooorrrrooo", "roorrororo", "ororrrroor");
        var result = Site.configureSite(siteList);
        assertThat(result).isNotEmpty();

        //when
        final var siteInstance = result.get();
        final Class<?> siteClass = siteInstance.getClass();
        try
        {
            Field siteField = siteClass.getDeclaredField("site");
            siteField.setAccessible(true);

            var site = (Terrain[][])siteField.get(siteInstance);

            var plainObjectCount = Arrays.stream(site)
                    .flatMap(t -> Arrays.stream(t))
                    .filter(terrain -> terrain instanceof PlainTerrain)
                    .count();
            var rockyObjectCount = Arrays.stream(site)
                    .flatMap(t -> Arrays.stream(t))
                    .filter(terrain -> terrain instanceof RockyTerrain)
                    .count();

            //then
            assertThat(rockyObjectCount).isEqualTo(19L);
            assertThat(plainObjectCount).isEqualTo(21L);

        }catch (NoSuchFieldException nsfe)
        {
            fail();
        }catch (IllegalAccessException ie)
        {
            fail();
        }

    }



}