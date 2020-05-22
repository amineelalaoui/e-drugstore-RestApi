import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import controller.CatalogController;
import controller.ClientController;

@ApplicationPath("/rest")
public class MainApplication extends Application {
	
	
	@Override
	public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
	
	private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ClientController.class);
        resources.add(CatalogController.class);
	}

}
