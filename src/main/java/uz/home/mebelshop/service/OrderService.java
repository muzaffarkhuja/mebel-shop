package uz.home.mebelshop.service;

import uz.home.mebelshop.dto.OrderDto;
import uz.home.mebelshop.dto.ResponseDto;

import java.util.List;

public interface OrderService {
    ResponseDto<List<OrderDto>> getAll();
    ResponseDto<OrderDto> getById(Integer id);
    ResponseDto<OrderDto> add(OrderDto orderDto);
    ResponseDto<OrderDto> update(OrderDto orderDto);
    ResponseDto<OrderDto> delete(Integer id);
}
