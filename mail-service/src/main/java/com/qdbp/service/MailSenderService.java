package com.qdbp.service;

import com.qdbp.dto.MailParams;

public interface MailSenderService {

    void send(MailParams mailParams);
}
