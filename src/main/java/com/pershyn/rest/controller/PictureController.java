package com.pershyn.rest.controller;

import com.pershyn.rest.authentication.AuthenticationFacade;
import com.pershyn.rest.model.Picture;
import com.pershyn.rest.model.Profile;
import com.pershyn.rest.model.User;
import com.pershyn.rest.service.DBPictureStorageService;
import com.pershyn.rest.service.PictureFileStorageService;
import com.pershyn.rest.service.ProfileService;
import com.pershyn.rest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PictureController {
    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureFileStorageService fileStorageService;

    @Autowired
    private DBPictureStorageService DBFileStorageService;

    @Autowired
    AuthenticationFacade authenticationFacade;

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;

//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//        String fileName = fileStorageService.storeFile(file);
//
//        DBFile dbFile = DBFileStorageService.storeFile(file);
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(dbFile.getId())
//                .toUriString();
//
//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }


    @PostMapping("/uploadFile")
    public Picture uploadImage(@RequestParam("file") MultipartFile file) {
        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
//        User user = new User();
        User user = userService.getUserByName(username);

        Profile profile = user.getProfile();

        String fileName = fileStorageService.storeFile(file);

        Picture picture = DBFileStorageService.storeFile(file);

        //picture.setProfile_is(profile.getId());

        return picture;
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

//    @GetMapping("/downloadFile/{fileId}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
//        // Load file from database
//        DBFile dbFile = DBFileStorageService.getFile(fileId);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
//                .body(new ByteArrayResource(dbFile.getData()));
//    }

}
