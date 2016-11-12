package com.wf.dcs.app.config;

import com.wf.dcs.app.repository.base.impl.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ddevera
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = BaseJpaRepositoryImpl.class,
        basePackages = "com.wf.dcs.app.repository", repositoryImplementationPostfix = "CustomImpl")
public class ApplicationConfig {
}
