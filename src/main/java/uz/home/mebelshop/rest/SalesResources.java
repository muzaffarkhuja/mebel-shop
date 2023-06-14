package uz.home.mebelshop.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.dto.SalesDto;
import uz.home.mebelshop.service.impl.SalesServiceImpl;

import java.util.List;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SalesResources {

    private final SalesServiceImpl salesServiceImpl;

    @PostMapping
    public ResponseDto<SalesDto> add(@RequestBody SalesDto salesDto){
        return salesServiceImpl.add(salesDto);
    }

    @GetMapping()
    public ResponseDto<List<SalesDto>> getAllSales(){
        return salesServiceImpl.getAllSales();
    }

    @GetMapping("{id}")
    public ResponseDto<SalesDto> getById(@PathVariable Integer id){
        return salesServiceImpl.getById(id);
    }

    @PatchMapping
    public ResponseDto<SalesDto> update(@RequestBody SalesDto salesDto){
        return salesServiceImpl.update(salesDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto<Void> delete(@PathVariable Integer id){
        return salesServiceImpl.delete(id);
    }
}