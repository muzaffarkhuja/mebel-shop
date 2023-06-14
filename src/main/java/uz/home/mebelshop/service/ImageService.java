package uz.home.mebelshop.service;

import org.springframework.web.multipart.MultipartFile;
import uz.home.mebelshop.dto.ResponseDto;

import java.io.IOException;

public interface ImageService {
    ResponseDto<Integer> fileUpload(MultipartFile file);
    ResponseDto<byte[]> getFileById(Integer fileId) throws IOException;
    String filePath(String folder, String ext);
}
