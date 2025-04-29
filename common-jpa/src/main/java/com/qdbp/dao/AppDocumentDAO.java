package com.qdbp.dao;

import com.qdbp.entity.AppDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDocumentDAO extends JpaRepository<AppDocument, Long> {
}
