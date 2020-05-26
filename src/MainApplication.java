import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import controller.CatalogController;
import controller.CategoryController;
import controller.ClientController;
import controller.ProductController;

@ApplicationPath("/rest")
public class MainApplication extends Application {
	
	Set<Object> singletons;
	
	@Override
	public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ClientController.class);
        resources.add(CatalogController.class);
        resources.add(ProductController.class);
        resources.add(CategoryController.class);
	}
	
	@Override
	public Set<Object> getSingletons() {
		if(singletons == null) {
			CorsFilter corsFilter = new CorsFilter();
            corsFilter.getAllowedOrigins().add("*");

            singletons = new LinkedHashSet<Object>();
            singletons.add(corsFilter);
		}
		return singletons;
	}

}
