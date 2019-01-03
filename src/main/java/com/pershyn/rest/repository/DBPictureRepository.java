package com.pershyn.rest.repository;

import com.pershyn.rest.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBPictureRepository extends JpaRepository<Picture, String> {
}
