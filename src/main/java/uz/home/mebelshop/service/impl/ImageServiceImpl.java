package uz.home.mebelshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.home.mebelshop.dto.ResponseDto;
import uz.home.mebelshop.model.Image;
import uz.home.mebelshop.repository.ImageRepository;
import uz.home.mebelshop.service.ImageService;
import uz.home.mebelshop.service.mapper.ImageMapper;
import uz.home.mebelshop.service.validator.AppStatusCodes;
import uz.home.mebelshop.service.validator.AppStatusMessages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public ResponseDto<Integer> fileUpload(MultipartFile file) {
        Image imageEntity = new Image();
        imageEntity.setExt(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        imageEntity.setCreatedAt(LocalDateTime.now());

        imageEntity.setPath(saveImage(file, imageEntity.getExt()));

        try {
            Image savedFile = imageRepository.save(imageEntity);

            return ResponseDto.<Integer>builder()
                    .data(savedFile.getId())
                    .message("OK")
                    .success(true)
                    .build();
        } catch (Exception e) {
            return ResponseDto.<Integer>builder()
                    .code(AppStatusCodes.DATABASE_ERROR_CODE)
                    .message(AppStatusMessages.DATABASE_ERROR + ": " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<byte[]> getFileById(Integer fileId) throws IOException {
        if (fileId == null) {
            return ResponseDto.<byte[]>builder()
                    .message(AppStatusMessages.NULL_VALUE)
                    .code(AppStatusCodes.VALIDATION_ERROR_CODE)
                    .build();
        }


        Optional<Image> optional = imageRepository.findById(fileId);

        if (optional.isEmpty()) {
            return ResponseDto.<byte[]>builder()
                    .message(AppStatusMessages.NOT_FOUND)
                    .code(AppStatusCodes.NOT_FOUND_ERROR_CODE)
                    .build();
        }
        String imagePath = optional.get().getPath();

        byte[] file = new FileInputStream(imagePath).readAllBytes();

        return ResponseDto.<byte[]>builder()
                .message(AppStatusMessages.OK)
                .code(AppStatusCodes.OK_CODE)
                .data(file)
                .success(true)
                .build();
    }

    private String saveImage(MultipartFile file, String ext) {
        String filePath;
        try {
            Files.copy(file.getInputStream(), Path.of(filePath = filePath("upload/"+"images/", ext)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public String filePath(String folder, String ext) {
        LocalDate localDate = LocalDate.now();
        String path = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File file = new File(folder + "/" + path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();

        return file.getPath() + "\\" + uuid + ext;

    }
}
