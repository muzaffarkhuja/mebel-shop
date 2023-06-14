package uz.home.mebelshop.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.home.mebelshop.dto.CategoryDto;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.service.impl.CategoryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryResources {

    private final CategoryServiceImpl categoryServiceImpl;

    @PostMapping("add")
    public ResponseDto<CategoryDto> add(@RequestBody CategoryDto categoryDto){
        return categoryServiceImpl.add(categoryDto);
    }

    @GetMapping()
    public ResponseDto<List<CategoryDto>> getAll(){
        return categoryServiceImpl.getAll();
    }

    @GetMapping("{id}")
    public ResponseDto<CategoryDto> getById(@PathVariable Integer id){
        return categoryServiceImpl.getById(id);
    }

    @GetMapping()
    public ResponseDto<List<CategoryDto>> getByParentId(@RequestParam Integer parentId){
        return categoryServiceImpl.getByParentId(parentId);
    }
    @DeleteMapping("delete")
    public ResponseDto<Void> delete(@RequestParam Integer id){
        return categoryServiceImpl.delete(id);
    }

    @PatchMapping
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto categoryDto){
        return categoryServiceImpl.update(categoryDto);
    }
}
