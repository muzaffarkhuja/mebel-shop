package uz.home.mebelshop.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.home.mebelshop.dto.OrderDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderResources {
    private final OrderService orderService;
    @GetMapping
    public ResponseDto<List<OrderDto>> getAll(){
        return orderService.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<OrderDto> getById(@PathVariable Integer id){
        return orderService.getById(id);
    }

    @PostMapping
    public ResponseDto<OrderDto> add(@RequestBody OrderDto orderDto){
        return orderService.add(orderDto);
    }

    @PatchMapping
    public ResponseDto<OrderDto> update(@RequestBody OrderDto orderDto){
        return orderService.update(orderDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<OrderDto> delete(@PathVariable Integer id){
        return orderService.delete(id);
    }
}
