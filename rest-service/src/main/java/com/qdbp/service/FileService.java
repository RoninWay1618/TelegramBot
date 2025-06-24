package com.qdbp.service;

import com.qdbp.entity.AppDocument;
import com.qdbp.entity.AppPhoto;


public interface FileService {

    AppDocument getDocument(String id);

    AppPhoto getPhoto(String id);
}
