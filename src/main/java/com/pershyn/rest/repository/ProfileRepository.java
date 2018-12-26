package com.pershyn.rest.repository;

import com.pershyn.rest.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
