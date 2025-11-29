package com.floweytech.agrotrack.platform.organization.application.internal.seeders;

import com.floweytech.agrotrack.platform.organization.domain.model.commands.CreatePlantTypeCommand;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypeId;
import com.floweytech.agrotrack.platform.organization.domain.model.valueobject.PlantTypes;
import com.floweytech.agrotrack.platform.organization.infrastructure.persistence.jpa.repositories.PlantTypeRepository;
import com.floweytech.agrotrack.platform.organization.domain.model.entities.PlantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PlantTypeSeeder implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PlantTypeSeeder.class);
    private final PlantTypeRepository plantTypeRepository;

    public PlantTypeSeeder(PlantTypeRepository plantTypeRepository) {
        this.plantTypeRepository = plantTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting PlantType seeding...");

        // Verificar si ya existen tipos de plantas predefinidos
        long existingCount = plantTypeRepository.findAllByPredefinedTrue().size();

        if (existingCount > 0) {
            logger.info("Predefined plant types already exist. Skipping seeding.");
            return;
        }

        // Definir los datos iniciales para cada tipo de planta
        seedPlantType(PlantTypes.POTATO, "Potato", "Root vegetable with high starch content, ideal for temperate climates");
        seedPlantType(PlantTypes.CORN, "Corn", "Cereal grain that requires warm weather and plenty of sunlight");
        seedPlantType(PlantTypes.WHEAT, "Wheat", "Cereal grain used primarily for flour production");
        seedPlantType(PlantTypes.BARLEY, "Barley", "Versatile cereal grain used for animal feed and brewing");
        seedPlantType(PlantTypes.COFFEE, "Coffee", "Tropical plant producing beans used for coffee beverages");
        seedPlantType(PlantTypes.RICE, "Rice", "Staple cereal grain that grows in flooded fields");
        seedPlantType(PlantTypes.TOMATO, "Tomato", "Fruit vegetable requiring warm temperatures and full sun");
        seedPlantType(PlantTypes.LETTUCE, "Lettuce", "Leafy vegetable that prefers cool weather conditions");
        seedPlantType(PlantTypes.CARROT, "Carrot", "Root vegetable that grows best in loose, well-drained soil");

        logger.info("PlantType seeding completed successfully. Total predefined types: {}",
                    plantTypeRepository.findAllByPredefinedTrue().size());
    }

    private void seedPlantType(PlantTypes plantType, String name, String description) {
        try {
            // Verificar si ya existe este tipo específico
            if (plantTypeRepository.findByName(name).isPresent()) {
                logger.debug("PlantType {} already exists, skipping", name);
                return;
            }

            // Crear el comando con plantTypeId temporal (se generará el ID real en @PostPersist)
            var command = new CreatePlantTypeCommand(
                    new PlantTypeId(0L), // Temporal, se actualizará después de persistir
                    plantType,
                    name,
                    description
            );

            // Crear y guardar la entidad
            var plantTypeEntity = new PlantType(command);
            plantTypeEntity.markAsPredefined(); // Marcar como predefinido
            plantTypeRepository.save(plantTypeEntity);

            logger.info("Created predefined PlantType: {} - {}", plantType, name);
        } catch (Exception e) {
            logger.error("Error creating PlantType {}: {}", name, e.getMessage());
        }
    }
}

