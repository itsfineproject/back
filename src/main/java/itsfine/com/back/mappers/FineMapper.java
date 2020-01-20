package itsfine.com.back.mappers;

import itsfine.com.back.dtos.Fine;
import itsfine.com.back.entities.FineEntity;
import itsfine.com.back.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FineMapper extends AbstractMapper<FineEntity, Fine> {

    @Autowired
    private CarRepository carRepository;

    public FineMapper(Class<FineEntity> entityClass, Class<Fine> dtoClass) {
        super(entityClass, dtoClass);
    }

    public void setupMapper(){
        modelMapper.createTypeMap(Fine.class, FineEntity.class)
                .addMappings(m->m.skip(FineEntity::setCar))
                .setPostConverter(toEntityConverter());
        modelMapper.createTypeMap(FineEntity.class, Fine.class)
                .addMappings(m->m.skip(Fine::setCarNumber))
                .setPostConverter(toDtoConverter());
    }

    @Override
    void mapSpecificFieldsToEntity(Fine fine, FineEntity fineEntity) {
        fineEntity.setCar(carRepository.findByCarNumber(fine.getCarNumber()));
    }

    @Override
    void mapSpecificFieldsToDto(FineEntity fineEntity, Fine fine) {
        fine.setCarNumber(fineEntity.getCar().getCarNumber());
    }
}
