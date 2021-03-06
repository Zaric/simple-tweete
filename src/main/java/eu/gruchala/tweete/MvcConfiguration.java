package eu.gruchala.tweete;

import freemarker.template.utility.XmlEscape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Map;
import java.util.Properties;

@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    final static Logger L = LoggerFactory.getLogger(MvcConfiguration.class);
    private static final String TEMPLATE_LOADER_PATH = "/WEB-INF/views/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/**");
    }

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        final FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        final FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath(TEMPLATE_LOADER_PATH);
        if (L.isDebugEnabled()) L.debug("Looking for ftl views at: " + TEMPLATE_LOADER_PATH);
        final Properties settings = new Properties();
        settings.setProperty("number_format", "0.######");
        freeMarkerConfigurer.setFreemarkerSettings(settings);
        final Map<String, Object> variables = new java.util.HashMap<String, Object>();
        variables.put("xml_escape", new XmlEscape());
        freeMarkerConfigurer.setFreemarkerVariables(variables);
        return freeMarkerConfigurer;
    }
}
