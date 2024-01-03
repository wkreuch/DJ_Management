package br.com.wkreuch.files.actions;

import br.com.wkreuch.configs.FilePortfolioConfig;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;

@Service
public class FilePortfolioStorage extends FileStorage {

    public FilePortfolioStorage(FilePortfolioConfig config) {
        super(Paths.get(config.getUploadDir()).toAbsolutePath().normalize());
    }

}
