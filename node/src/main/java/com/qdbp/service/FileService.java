package com.qdbp.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import com.qdbp.entity.AppDocument;

public interface FileService {
    AppDocument processDoc(Message externalMessage);
}
