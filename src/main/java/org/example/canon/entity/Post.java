package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.canon.dto.PostDTO;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Base {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    private String category;

    private String imageURL;

    private byte isComfirmed;

    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    private User user;


    public static Post of(PostDTO dto , User user){
        return Post.builder()
                .content(dto.getContent())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .imageURL(dto.getImageURL())
                .contact(dto.getContact())
                .user(user)
                .build();
    }

}