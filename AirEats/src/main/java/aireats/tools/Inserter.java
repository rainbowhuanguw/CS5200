package aireats.tools;

import aireats.dal.*;

import java.io.IOException;

public class Inserter {

    public static void main(String[] args) throws IOException {
        AttributesDao attributesDao = AttributesDao.getInstance();
        CategoriesDao categoriesDao = CategoriesDao.getInstance();
        HostsDao hostsDao = HostsDao.getInstance();
        HoursDao hoursDao = HoursDao.getInstance();
        RecommendationsDao recommendationDao = RecommendationsDao.getInstance();
        TipsDao tipsDao = TipsDao.getInstance();
        YelpUsersDao yelpUsersDao = YelpUsersDao.getInstance();

        CSVReader.read("src/data/hosts.csv");
    }

}