package test;

import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchEmptyPage;
import pages.SearchResultPage;
import tools.TestRunner;

import java.util.ArrayList;
import java.util.List;



public class TestSearch extends TestRunner {

    @Description("Test elements in search page")
    @Test
    public void TestElementsInSearch() {

        List<String> expected = new ArrayList<String>();
        expected.add("Relevance");
        expected.add("Name, A to Z");
        expected.add("Name, Z to A");
        expected.add("Price, low to high");
        expected.add("Price, high to low");

        List<String> actual = new ArrayList<String>();

        String text = "mug";

        SearchResultPage page = new SearchResultPage(driver);

        page.setTextSearchField(text);
        page.clickSearchButton();
        page.clickSortButtonBy();

        page.setListSortsEelements(page.getDdmenu());
        System.out.println("\n\n====setListSortsEelements====");
        for (WebElement el : page.getListSortElements()) {
            System.out.println(el);
            actual.add(el.getText());
        }

        Assert.assertEquals(expected, actual);

    }

    @Description("Test empty page")
    @Test
    public void TestEmptyPage() {
        System.out.println("\n");
        String text = "asfasf";

        SearchEmptyPage page = new SearchEmptyPage(driver);
        page.setTextSearchField(text);
        page.clickSearchButton();
        String expected = "Sorry for the inconvenience.";
        System.out.println("expected result: " + expected);
        System.out.println("actual :" + page.getTextSearchResultStatus());
        Assert.assertEquals(expected, page.getTextSearchResultStatus());

    }

    @Description("Test page after search]")
    @Test
    public void TetsAfterSearch() {

        List<String> actual = new ArrayList<String>();
        String text = "mug";
        SearchResultPage page = new SearchResultPage(driver);
        page.setTextSearchField(text);
        page.clickSearchButton();

        for (WebElement el : page.getSearchResult()) {
            actual.add(el.getText().toLowerCase());
            System.out.println(el.getText());
        }
        Assert.assertTrue(actual.get(2).contains(text));
    }


    @Description("Test search result and sort AZ")
    @Test
    public void TestSearchResultSortAZ() {
        SearchResultPage page = new SearchResultPage(driver);
        List<String> expected = new ArrayList<String>();
        expected.add("Customizable Mug");
        expected.add("Mug The Best Is Yet To Come");
        expected.add("Mug The Adventure Begins");
        expected.add("Mug Today Is A Good Day");
        expected.add("Pack Mug + Framed Poster");

        List<String> actual = new ArrayList<String>();
        String text = "mug";

        page.setTextSearchField(text);
        page.clickSearchButton();
        page.clickSortButtonBy();
        page.setListSortsEelements(page.getDdmenu());
        page.getListSortElements().get(1).click();


        for (WebElement el : page.getSearchResult()) {
            actual.add(el.getText().trim());
            System.out.println(el.getText());
        }


        Assert.assertEquals(expected, actual);

    }



    @Description("Test not work for example")
    @Test
    public void TestSearchResultSortAZNotWork() {
        SearchResultPage page = new SearchResultPage(driver);
        List<String> expected = new ArrayList<String>();
        expected.add("Pack Mug + Framed Poster");
        expected.add("Customizable Mug");
        expected.add("Mug The Best Is Yet To Come");
        expected.add("Mug The Adventure Begins");
        expected.add("Mug Today Is A Good Day");


        List<String> actual = new ArrayList<String>();
        String text = "mug";

        page.setTextSearchField(text);
        page.clickSearchButton();
        page.clickSortButtonBy();
        page.setListSortsEelements(page.getDdmenu());
        page.getListSortElements().get(1).click();

        for (WebElement el : page.getSearchResult()) {
            actual.add(el.getText().trim());
            System.out.println(el.getText());
        }


        Assert.assertEquals(expected, actual);

    }


}
