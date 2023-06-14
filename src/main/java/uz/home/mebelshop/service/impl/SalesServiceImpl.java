package uz.home.mebelshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.home.mebelshop.dto.ProductDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.dto.SalesDto;
import uz.home.mebelshop.model.Product;
import uz.home.mebelshop.model.Sales;
import uz.home.mebelshop.repository.SalesRepository;
import uz.home.mebelshop.service.SalesService;
import uz.home.mebelshop.service.mapper.SalesMapper;

import java.util.List;
import java.util.Optional;

import static uz.home.mebelshop.service.validator.AppStatusCodes.*;
import static uz.home.mebelshop.service.validator.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;
    private final SalesMapper salesMapper;

    public ResponseDto<SalesDto> add(SalesDto salesDto) {
        try{
            Sales sales = salesMapper.toEntity(salesDto);
            salesRepository.save(sales);

            return ResponseDto.<SalesDto>builder()
                    .success(true)
                    .message(OK)
                    .data(salesMapper.toDto(sales))
                    .build();
        } catch (Exception e){
            return ResponseDto.<SalesDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }


    public ResponseDto<List<SalesDto>> getAllSales() {
        try{
            return ResponseDto.<List<SalesDto>>builder()
                    .success(true)
                    .message(OK)
                    .data(salesRepository.findAll().stream().map(salesMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<SalesDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<SalesDto> getById(Integer id) {
        try {
            return salesRepository.findById(id)
                    .map(sales -> ResponseDto.<SalesDto>builder()
                            .success(true)
                            .message(OK)
                            .data(salesMapper.toDto(sales))
                            .build())
                    .orElse(ResponseDto.<SalesDto>builder()
                            .code(NOT_FOUND_ERROR_CODE)
                            .message(NOT_FOUND)
                            .build());
        } catch (Exception e){
            return ResponseDto.<SalesDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<SalesDto> update(SalesDto salesDto) {
        if(salesDto.getId() == null){
            return ResponseDto.<SalesDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("Id is null")
                    .build();
        }

        Optional<Sales> optional = salesRepository.findById(salesDto.getId());

        if(optional.isEmpty()){
            return ResponseDto.<SalesDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        Sales sales = optional.get();

        if(salesDto.getPrice() != null){
            sales.setPrice(salesDto.getPrice());
        }
        if(salesDto.getExpireDate() != null){
            sales.setExpireDate(salesDto.getExpireDate());
        }

        try{
            salesRepository.save(sales);

            return ResponseDto.<SalesDto>builder()
                    .success(true)
                    .message(OK)
                    .data(salesMapper.toDto(sales))
                    .build();
        } catch (Exception e){
                return ResponseDto.<SalesDto>builder()
                        .code(DATABASE_ERROR_CODE)
                        .message(DATABASE_ERROR + " -> " + e.getMessage())
                        .build();
        }
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        if(id == null){
            return ResponseDto.<Void>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("ID is null")
                    .build();
        }
        Optional<Sales> optional = salesRepository.findById(id);

        if(optional.isEmpty()){
            return ResponseDto.<Void>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        try {
            salesRepository.deleteById(id);

            return ResponseDto.<Void>builder()
                    .success(true)
                    .message(OK)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Void>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }
}
