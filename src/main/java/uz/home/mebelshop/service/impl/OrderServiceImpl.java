package uz.home.mebelshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.home.mebelshop.dto.OrderDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.model.Orders;
import uz.home.mebelshop.repository.OrderRepository;
import uz.home.mebelshop.service.OrderService;
import uz.home.mebelshop.service.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;

import static uz.home.mebelshop.service.validator.AppStatusCodes.*;
import static uz.home.mebelshop.service.validator.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public ResponseDto<List<OrderDto>> getAll() {
        List<Orders> orders = orderRepository.findAll();

        return ResponseDto.<List<OrderDto>>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(orders.stream().map(orderMapper::toDto).toList())
                .build();
    }

    @Override
    public ResponseDto<OrderDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<OrderDto>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Orders> byId = orderRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<OrderDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        return ResponseDto.<OrderDto>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(orderMapper.toDto(byId.get()))
                .build();
    }

    @Override
    public ResponseDto<OrderDto> add(OrderDto orderDto) {
        try{
            Orders orders = orderMapper.toEntity(orderDto);
            orderRepository.save(orders);

            return ResponseDto.<OrderDto>builder()
                    .success(true)
                    .message(OK)
                    .data(orderMapper.toDto(orders))
                    .build();
        } catch (Exception e){
            return ResponseDto.<OrderDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<OrderDto> update(OrderDto orderDto) {
        return null;
    }

    @Override
    public ResponseDto<OrderDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<OrderDto>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        Optional<Orders> byId = orderRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<OrderDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }
        try {
            orderRepository.deleteById(id);

            return ResponseDto.<OrderDto>builder()
                    .success(true)
                    .message(OK)
                    .data(orderMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<OrderDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }
}
