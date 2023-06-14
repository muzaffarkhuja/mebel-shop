package uz.home.mebelshop.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.service.impl.ImageServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageResources {
    private final ImageServiceImpl imageService;
    @PostMapping
    public ResponseDto<Integer> uploadFile(@RequestPart("file") MultipartFile file){
        return imageService.fileUpload(file);
    }

    @GetMapping
    public ResponseDto<byte[]> getFileById(@RequestParam Integer fileId) throws IOException {
        return imageService.getFileById(fileId);
    }
}