package org.avenuecode.assessment.config;

import org.avenuecode.assessment.resources.OrderResource;
import org.avenuecode.assessment.resources.ProductResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig{

    /*Configuration file for Jersey. You need to register the classes which uses
    jersey annotations here to let scan all the annotations related to Jersey.*/
    public JerseyConfig()
    {
        packages(getClass().getPackage().getName() + ".resources");
        register(ProductResource.class);
        register(OrderResource.class);

    }
}
