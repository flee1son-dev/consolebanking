package sorokin.java.course.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfiguration {
    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.username}")
    private String dbUsername;

    @Value("${db.password}")
    private String dbPassword;

    @Value("${db.dialect}")
    private String dbDialect;

    @Value("${hibernate.show_sql}")
    private String showSQL;

    @Value("${hibernate.format_sql}")
    private String formatSQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.current_session_context_class}")
    private String currentSessionContextClass;


    @Bean
    public SessionFactory sessionFactory(){
        org.hibernate.cfg.Configuration configuration =
                new org.hibernate.cfg.Configuration();
        configuration
                .addPackage("sorokin.java.course")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", dbUrl)
                .setProperty("hibernate.connection.username", dbUsername)
                .setProperty("hibernate.connection.password", dbPassword)
                .setProperty("hibernate.dialect",dbDialect)
                .setProperty("hibernate.show_sql", showSQL)
                .setProperty("hibernate.format_sql", formatSQL)
                .setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto)
                .setProperty("hibernate.current_session_context_class", currentSessionContextClass);

        return configuration.buildSessionFactory();
    }
}
