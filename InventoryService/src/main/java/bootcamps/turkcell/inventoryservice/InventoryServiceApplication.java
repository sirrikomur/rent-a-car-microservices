package bootcamps.turkcell.inventoryservice;

import bootcamps.turkcell.common.utilities.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Inventory.ServiceBasePackage})
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

}
