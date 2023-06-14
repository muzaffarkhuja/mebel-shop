package uz.home.mebelshop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.home.mebelshop.dto.SalesDto;
import uz.home.mebelshop.model.Sales;

@Mapper(componentModel = "spring")
public abstract class SalesMapper implements CommonMapper<SalesDto, Sales> {

    @Autowired
    protected ProductMapper productMapper;

    @Mapping(target = "product", expression = "java(productMapper.toDto(sales.getProduct()))")
    public abstract SalesDto toDto(Sales sales);
}
