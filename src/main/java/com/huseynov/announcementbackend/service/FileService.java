package com.huseynov.announcementbackend.service;

import com.huseynov.announcementbackend.dao.FileDao;
import com.huseynov.announcementbackend.dto.FileResponse;
import com.huseynov.announcementbackend.entity.File;
import com.huseynov.announcementbackend.mapper.FileMapper;
import com.huseynov.announcementbackend.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author : Dunay Gudratli
 * @since : 08.04.2025
 **/
@Service
@RequiredArgsConstructor
public class FileService {
    private final FileDao fileDao;
    private final FileMapper fileMapper;

    public File add(MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
            var fileName = multipartFile.getOriginalFilename();//picture.jpg
            var fileType = multipartFile.getContentType();//image/jpeg

            //save file physically
            FileUtil.saveFile(fileName, multipartFile);

            //save file information to database
            File file = new File();
            file.setName(fileName);
            file.setType(fileType);
            file.setCreatedAt(LocalDateTime.now());
            file = fileDao.add(file);

            return file;
        }
        return null;
    }

    public FileResponse downloadFile(File file) {
        byte[] arr = FileUtil.readFile(file.getName());
        return fileMapper.toFileResponse(file, arr);
    }

    public void deleteFile(File file) {
        FileUtil.deleteFile(file.getName());
        fileDao.remove(file.getFileId());
    }
}

