package org.example.canon.controller;

import lombok.RequiredArgsConstructor;
import org.example.canon.controller.response.postLikeResponse.LikeIdResponse;
import org.example.canon.controller.response.postLikeResponse.LikeTrueOrFalse;
import org.example.canon.controller.response.postLikeResponse.PostLikeListResponse;
import org.example.canon.dto.CustomOAuth2UserDTO;
import org.example.canon.dto.PostLikeDTO;
import org.example.canon.entity.PostLike;
import org.example.canon.service.PostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
@Controller
@RequiredArgsConstructor
public class LikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/add/{postId}")
    public ResponseEntity<LikeIdResponse> addLike(@AuthenticationPrincipal CustomOAuth2UserDTO userDto, @PathVariable Long postId) {
        PostLike postLike = postLikeService.addLike(userDto.getEmail(), postId);
        LikeIdResponse response = new LikeIdResponse(postLike.getPostLikeId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byPost/{postId}")
    public ResponseEntity<LikeTrueOrFalse> getAllLikeByPost(@PathVariable Long postId, @AuthenticationPrincipal CustomOAuth2UserDTO userDto) {

        Boolean hasLiked = postLikeService.checkUserLikesByPost(postId, userDto.getEmail());


        String message = hasLiked ? "Liked." : "Not Liked";
        LikeTrueOrFalse response = new LikeTrueOrFalse(message);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/byUser/{userId}")
    public ResponseEntity<PostLikeListResponse> getAllLikeByUser(@PathVariable Long userId) {
        List<PostLikeDTO> postLikes = postLikeService.getAllForLikesByUser(userId);
        PostLikeListResponse response = new PostLikeListResponse(postLikes);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteLike(
            @AuthenticationPrincipal CustomOAuth2UserDTO userDto, @PathVariable Long postId) {
        postLikeService.deleteLike(postId, userDto);
        return ResponseEntity.ok().build();
    }

}
