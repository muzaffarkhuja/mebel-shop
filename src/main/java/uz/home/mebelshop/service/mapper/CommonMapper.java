package uz.home.mebelshop.service.mapper;

public interface CommonMapper<Dto, Entity> {
    Dto toDto(Entity model);
    Entity toEntity(Dto dto);
}
