package org.egov.pg.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Getter
@ToString
@Configuration
@PropertySource("classpath:application.properties")
public class AppProperties {

    private final Integer earlyReconcileJobRunInterval;

    private final String saveTxnTopic;

    private final String updateTxnTopic;

    private final String saveTxnDumpTopic;

    private final String updateTxnDumpTopic;
    
    private final String idGenHost;

    private final String idGenPath;

    private final String idGenName;

    private final String idGenFormat;

    private final String billingServiceHost;

    private final String billingServicePath;

    private final String collectionServiceHost;

    private final String collectionServicePath;

    @Autowired
    public AppProperties(Environment environment){
        this.earlyReconcileJobRunInterval = Integer.valueOf(environment.getRequiredProperty("pg.earlyReconcileJobRunInterval.mins"));
        this.saveTxnTopic = environment.getRequiredProperty("persister.save.pg.txns");
        this.updateTxnTopic = environment.getRequiredProperty("persister.update.pg.txns");
        this.saveTxnDumpTopic = environment.getRequiredProperty("persister.save.pg.txnsDump");
        this.updateTxnDumpTopic = environment.getRequiredProperty("persister.update.pg.txnsDump");
        this.idGenHost = environment.getRequiredProperty("egov.idgen.host");
        this.idGenPath = environment.getRequiredProperty("egov.idgen.path");
        this.idGenName = environment.getRequiredProperty("egov.idgen.ack.name");
        this.idGenFormat = environment.getRequiredProperty("egov.idgen.ack.format");
        this.billingServiceHost = environment.getRequiredProperty("egov.billingservice.host");
        this.billingServicePath = environment.getRequiredProperty("egov.billingservice.path");
        this.collectionServiceHost = environment.getRequiredProperty("egov.collectionservice.host");
        this.collectionServicePath = environment.getRequiredProperty("egov.collectionservice.path");
    }

}
