package org.eurekaj.simpledb;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.ListDomainsResult;
import org.eurekaj.api.dao.*;
import org.eurekaj.simpledb.dao.SimpleDBAlertDao;
import org.eurekaj.simpledb.dao.SimpleDBGroupedStatisticsDao;
import org.eurekaj.simpledb.dao.SimpleDBSmtpDao;
import org.eurekaj.simpledb.dao.SimpleDBTreeMenuDao;
import org.eurekaj.spi.db.EurekaJDBPluginService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: joahaa
 * Date: 5/7/11
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDBEnv extends EurekaJDBPluginService {
    private AlertDao alertDao;
    private GroupedStatisticsDao groupedStatisticsDao;
    private LiveStatisticsDao liveStatisticsDao;
    private SmtpDao smtpDao;
    private TreeMenuDao treeMenuDao;
    private AmazonSimpleDB amazonSimpleDB;

    private final String[] validDomainNameArray = {"EurekaJ_Alert",
            "EurekaJ_Smtp",
            "EurekaJ_GroupedStatistics",
            "EurekaJ_LiveStatistics",
            "EurekaJ_TreeMenuNode",
            "EurekaJ_TriggeredAlert"};


    @Override
    public String getPluginName() {
        return "Amazon SimpleDB";
    }

    @Override
    public void setup() {
        //TODO: Connect to Amazon SimpleDB
        this.connectToAmazonSimpleDB();
        this.checkAndInitializeDomains();

        alertDao = new SimpleDBAlertDao(amazonSimpleDB);
        groupedStatisticsDao = new SimpleDBGroupedStatisticsDao(amazonSimpleDB);
        liveStatisticsDao = new SimpleDBTreeMenuDao(amazonSimpleDB);
        smtpDao = new SimpleDBSmtpDao(amazonSimpleDB);
        treeMenuDao = new SimpleDBTreeMenuDao(amazonSimpleDB);
    }

    private void connectToAmazonSimpleDB() {
        amazonSimpleDB = new AmazonSimpleDBClient(new BasicAWSCredentials("AKIAITQ7EBJPKHONOTEQ", "UKM3SfWg/2DJveNc1UlD3g01ISHiEcKISe1Nlh6D"));
    }

    private void checkAndInitializeDomains() {
        ListDomainsResult domainsResult = amazonSimpleDB.listDomains();
        List<String> domainNameList = domainsResult.getDomainNames();

        for (String validDomainName : validDomainNameArray) {
            if (!domainNameList.contains(validDomainName)) {
                amazonSimpleDB.createDomain(new CreateDomainRequest(validDomainName));
            }
        }


    }

    @Override
    public void tearDown() {
        //TODO: Drop Connection to Amazon SimpleDB
    }

    @Override
    public AlertDao getAlertDao() {
        return alertDao;
    }

    @Override
    public GroupedStatisticsDao getGroupedStatisticsDao() {
        return groupedStatisticsDao;
    }

    @Override
    public LiveStatisticsDao getLiveStatissticsDao() {
        return liveStatisticsDao;
    }

    @Override
    public SmtpDao getSmtpDao() {
        return smtpDao;
    }

    @Override
    public TreeMenuDao getTreeMenuDao() {
        return treeMenuDao;
    }
}
