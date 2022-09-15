package org.example.BookSpring.bookStorage.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.logging.Logger;

@Configuration
public class OutputBeans {
    Logger logger = Logger.getLogger(OutputBeans.class.getName());

    @Autowired
    public void showBeans(ApplicationContext ctx) {

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
    }

}
