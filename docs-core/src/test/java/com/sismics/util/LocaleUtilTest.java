package com.sismics.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

public class LocaleUtilTest {

    @Test
    public void returnsEnglishForNull() {
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(null));
    }

    @Test
    public void returnsEnglishForEmptyString() {
        Assert.assertEquals(Locale.ENGLISH, LocaleUtil.getLocale(""));
    }

    @Test
    public void parsesLanguageOnly() {
        Locale locale = LocaleUtil.getLocale("fr");
        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void parsesLanguageAndCountry() {
        Locale locale = LocaleUtil.getLocale("fr_FR");
        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("FR", locale.getCountry());
        Assert.assertEquals("", locale.getVariant());
    }

    @Test
    public void parsesLanguageCountryAndVariant() {
        Locale locale = LocaleUtil.getLocale("fr_FR_EURO");
        Assert.assertEquals("fr", locale.getLanguage());
        Assert.assertEquals("FR", locale.getCountry());
        Assert.assertEquals("EURO", locale.getVariant());
    }
}
