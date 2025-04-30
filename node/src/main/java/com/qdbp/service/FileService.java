package com.qdbp.service;

import com.qdbp.entity.AppPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import com.qdbp.entity.AppDocument;

public interface FileService {
    AppDocument processDoc(Message telegramMessage);
    AppPhoto processPhoto(Message telegramMessage);
}
