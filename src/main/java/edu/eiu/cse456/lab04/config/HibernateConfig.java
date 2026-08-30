package edu.eiu.cse456.lab04.config;

import edu.eiu.cse456.lab04.entity.Customer;
import edu.eiu.cse456.lab04.entity.Invoice;
import edu.eiu.cse456.lab04.entity.InvoiceItem;
import edu.eiu.cse456.lab04.entity.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    @Bean(destroyMethod = "close")
    public SessionFactory sessionFactory() {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .loadProperties("hibernate.properties")
                        .build();

        MetadataSources sources = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Invoice.class)
                .addAnnotatedClass(InvoiceItem.class); // Đừng quên add class mới này nhé

        return sources.buildMetadata().buildSessionFactory();
    }
}
