package com.elearning.AILearning.aicontent.gemini;

import com.elearning.AILearning.aicontent.contentdto.ContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeminiRequestDto {

    private List<ContentDto> contents;
}