package br.com.wkreuch.files.actions;

import br.com.wkreuch.exceptions.FileStorageException;
import br.com.wkreuch.exceptions.MyFileNotFoundException;
import br.com.wkreuch.models.Portfolio;
import br.com.wkreuch.models.enums.TypePortfolio;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public abstract class FileStorage {

    private final Path fileStorageLocation;

    public FileStorage(Path fileStorageLocation) {

        this.fileStorageLocation = fileStorageLocation;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored!", e);
        }
    }



    public String storeFile(Portfolio portfolio, MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (portfolio.getTypePortfolio() == TypePortfolio.YOUTUBE) {
                throw new Exception("Type portfolio not valid for this operation");
            }

            if (filename.contains("..")) {
                throw new FileStorageException("Filename contains invalid path sequence " + filename);
            }

            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + filename + " Please try again", e);
        }
    }

    public Resource loadFileAsResource(String filename) {
        try {

            Path filePath = this.fileStorageLocation.resolve(filename).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) return resource;
            else throw new MyFileNotFoundException("File not found");
        } catch (Exception e) {
            throw new MyFileNotFoundException("File not found "+ filename, e);
        }
    }

}
