package org.example.canon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tools extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


    public static Tools of(String tool, Post post){
        return Tools.builder()
                .tool(tool)
                .post(post)
                .build();

}


    public static Tools of(String tool){
        return Tools.builder()
                .tool(tool)
                .build();
    }



}
