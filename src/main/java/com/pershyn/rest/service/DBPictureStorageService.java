package com.pershyn.rest.service;

import com.pershyn.rest.authentication.AuthenticationFacade;
import com.pershyn.rest.exception.FileStorageException;
import com.pershyn.rest.exception.MyFileNotFoundException;
import com.pershyn.rest.model.Picture;
import com.pershyn.rest.model.Profile;
import com.pershyn.rest.model.User;
import com.pershyn.rest.repository.DBPictureRepository;
import com.pershyn.rest.storage_properties.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DBPictureStorageService {

    @Autowired
    private DBPictureRepository dbPictureRepository;

    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;



    public Picture storeFile(MultipartFile file) {
        // Normalize file name
        String path = fileStorageProperties.getUploadDir();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        path = path + "/"+fileName;
        path = path.substring(28);


        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User user = userService.getUserByName(username);

        Profile profile = user.getProfile();



        System.out.println(fileName);
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Picture picture = new Picture(fileName, file.getContentType(), path);

            picture.setProfile_id(profile.getId());

            return dbPictureRepository.save(picture);
        } catch (Exception ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Picture getFile(String fileId) {
        return dbPictureRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
