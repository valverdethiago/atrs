package com.crossover.atrs.respository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages="com.crossover.atrs")
@PropertySource(value="classpath:atrs.properties")
public class DatabaseConfiguration extends AbstractMongoConfiguration{

    @Value("${database.host}")
    private String hostname;
    @Value("${database.name}")
    private String name;

	@Override
	protected String getDatabaseName() {
		return this.name;
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(this.hostname);
	}


}
