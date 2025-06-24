package com.qdbp.service.impl;

import com.qdbp.utils.CryptoTool;
import com.qdbp.dao.AppDocumentDAO;
import com.qdbp.dao.AppPhotoDAO;
import com.qdbp.entity.AppDocument;
import com.qdbp.entity.AppPhoto;
import com.qdbp.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;


@Log4j
@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {


    private final AppDocumentDAO appDocumentDAO;

    private final AppPhotoDAO appPhotoDAO;

    private final CryptoTool cryptoTool;

    @Override
    public AppDocument getDocument(String hash) {
        var id = cryptoTool.idOf(hash);
        if(id == null) {
            return null;
        }
        return appDocumentDAO.findById(id).orElse(null);
    }

    @Override
    public AppPhoto getPhoto(String hash) {
        var id = cryptoTool.idOf(hash);
        if(id == null) {
            return null;
        }
        return appPhotoDAO.findById(id).orElse(null);
    }

}
