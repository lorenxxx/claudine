package com.us.claudine.autoconfigure.datasource;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * Created by Loren on 2018/10/9.
 */
public class DataSourceAutoRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    public static final String DATASOURCE_PREFIX = "claudine.datasource";

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

    }

    private void initDataSourceName() {
        Binder binder = Binder.get(environment);
        List<String> post = binder.bind(DATASOURCE_PREFIX, Bindable.listOf(String.class)).get();


    }

}
