package com.huseynov.announcementbackend.dao.jpaImpl;

import com.huseynov.announcementbackend.dao.FileDao;
import com.huseynov.announcementbackend.entity.File;
import com.huseynov.announcementbackend.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileDaoJpaImpl implements FileDao {
    private final FileRepository fileRepository;

    @Override
    public File add(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void remove(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public Optional<File> findById(Long id) {
        return fileRepository.findById(id);
    }
}
