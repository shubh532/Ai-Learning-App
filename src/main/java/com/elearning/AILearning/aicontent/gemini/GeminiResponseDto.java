package com.elearning.AILearning.aicontent.gemini;

import com.elearning.AILearning.aicontent.contentdto.CandidateDto;
import lombok.Data;

import java.util.List;

@Data
public class GeminiResponseDto {

    private List<CandidateDto> candidates;
}
