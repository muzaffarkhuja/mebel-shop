package uz.home.mebelshop.service.mapper;

import org.mapstruct.Mapper;
import uz.home.mebelshop.dto.OrderDto;
import uz.home.mebelshop.model.Orders;

@Mapper(componentModel = "spring")
public abstract class OrderMapper implements CommonMapper<OrderDto, Orders> {
    @Override
    public abstract OrderDto toDto(Orders model);

    @Override
    public abstract Orders toEntity(OrderDto orderDto);
}
