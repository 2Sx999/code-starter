package cn.porkchop.springbootstarter.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class SpringDataMongoConfig extends AbstractMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public GridFsTemplate gridFsColorReportTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), "ColorReport");
    }

    @Bean
    public GridFsTemplate gridFsUserDefinePicTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), "UserDefinePic");
    }

    @Bean
    public GridFsTemplate gridFsSHStreetPicTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), "SHStreetPic");
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host);
    }
}
