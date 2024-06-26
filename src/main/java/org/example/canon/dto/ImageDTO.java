package org.example.canon.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.canon.entity.Image;
import org.example.canon.entity.ImageOnlyURL;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageDTO {

    private List<ImageOnlyURL> images;



}
