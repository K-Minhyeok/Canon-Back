package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.request.ProfileRequest;
import org.example.canon.controller.response.postResponse.PostResponse;
import org.example.canon.controller.response.profileResponse.ProfileResponse;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.ProfileDTO;
import org.example.canon.service.ProfileService;
import org.example.canon.service.S3Uploader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final S3Uploader s3Uploader;

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ProfileResponse> uploadProfile(@RequestParam("image") MultipartFile image,
//                                                                  ProfileRequest profileRequest,
//                                                                  @AuthenticationPrincipal CustomOAuth2UserDTO userDto) throws IOException {
//        String imageURL = s3Uploader.upload(image, "example");
//
//        ProfileDTO profileDTO = ProfileDTO.of(profileRequest, imageURL);
//
//        profileService.addProfile(profileDTO,userDto);
//        return null;
//    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileResponse> updateProfile(ProfileRequest profileRequest,
                                                          @AuthenticationPrincipal CustomOAuth2UserDTO userDto) throws IOException {



        ProfileDTO profileDTO = ProfileDTO.of(profileRequest);

        profileService.updateProfile(profileDTO, userDto);
        return null;
    }

//    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ProfileResponse> updateProfile(@RequestParam("image") MultipartFile image,
//                                                         ProfileRequest profileRequest,
//                                                         @AuthenticationPrincipal CustomOAuth2UserDTO userDto) throws IOException {
//
//        String imageURL = "123";
//
//        if( image != null) {
//            imageURL = s3Uploader.upload(image, "example");
//        }
//
//        ProfileDTO profileDTO = ProfileDTO.of(profileRequest, imageURL);
//
//        profileService.updateProfile(profileDTO, userDto);
//        return null;
//    }

    @GetMapping("/myinfo")
    public ResponseEntity<ProfileResponse> getMyInfo(@AuthenticationPrincipal CustomOAuth2UserDTO userDto){
        ProfileDTO profileDTO = profileService.getProfile(userDto);
        ProfileResponse response = new ProfileResponse(profileDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long userId){
        ProfileDTO profileDTO = profileService.getProfile(userId);
        ProfileResponse response = new ProfileResponse(profileDTO);
        return ResponseEntity.ok(response);
    }

}
