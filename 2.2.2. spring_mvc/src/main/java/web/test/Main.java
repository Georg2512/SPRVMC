package web.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.HibernateConfig;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        context.close();
    }
}
