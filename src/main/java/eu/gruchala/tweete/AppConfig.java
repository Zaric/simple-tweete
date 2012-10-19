package eu.gruchala.tweete;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MvcConfiguration.class)
@ComponentScan("eu.gruchala.tweete")
public class AppConfig {}
