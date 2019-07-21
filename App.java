package springbatch.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"springbatch.batch"})
@SpringBootApplication
public class App extends SpringBootServletInitializer 
{
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	
    @Bean
    public AppCommandRunner schedulerRunner() {
        return new AppCommandRunner();
    }
    
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
