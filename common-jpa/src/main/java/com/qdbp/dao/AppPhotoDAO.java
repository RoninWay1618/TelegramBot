package com.qdbp.dao;


import com.qdbp.entity.AppPhoto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppPhotoDAO extends JpaRepository<AppPhoto, Long> {
}
