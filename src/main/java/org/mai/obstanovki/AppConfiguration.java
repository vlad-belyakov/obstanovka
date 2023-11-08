package org.mai.obstanovki;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.mai.obstanovki")
@PropertySource("classpath: App.properties")
public class AppConfiguration {

    @Value("${filesTool.path}")
    String path;

    @Bean
    public FilesTool filesToolBean(){
        return new FilesTool(path);
    }

    @Bean
    @Autowired
    public Decoder decoderBean(){
        return new Decoder(new FilesTool(path));
    }

}
