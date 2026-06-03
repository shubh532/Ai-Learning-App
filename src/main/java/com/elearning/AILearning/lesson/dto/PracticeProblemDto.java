package com.elearning.AILearning.lesson.dto;

import com.elearning.AILearning.enums.DifficultyLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeProblemDto {

    private String title;

    private DifficultyLevel difficulty;
}