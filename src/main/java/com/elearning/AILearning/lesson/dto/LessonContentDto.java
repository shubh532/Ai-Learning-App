package com.elearning.AILearning.lesson.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonContentDto {

    private OverviewDto overview;

    private List<ConceptDto> coreConcepts;

    private List<ExampleDto> examples;

    private List<PitfallDto> pitfalls;

    private List<InterviewInsightDto> interviewInsights;

    private List<TrickyQuestionDto> trickyQuestions;

    private List<PracticeProblemDto> practiceProblems;

    private SummaryDto summary;
}