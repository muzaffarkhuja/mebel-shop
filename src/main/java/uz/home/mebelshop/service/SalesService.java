package uz.home.mebelshop.service;

import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.dto.SalesDto;

import java.util.List;

public interface SalesService {
    ResponseDto<SalesDto> add(SalesDto salesDto);
    ResponseDto<List<SalesDto>> getAllSales();
    ResponseDto<SalesDto> getById(Integer id);
    ResponseDto<SalesDto> update(SalesDto salesDto);
    ResponseDto<Void> delete(Integer id);
}
