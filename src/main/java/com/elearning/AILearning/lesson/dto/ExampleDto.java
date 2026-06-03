package com.elearning.AILearning.lesson.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleDto {

    private String language;

    private String code;

    private String explanation;
}
