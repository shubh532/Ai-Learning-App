package com.elearning.AILearning.service;

import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepo;

    public List<Topic> getTopicList(){
        return topicRepo.findByIsPublishedTrueAndIsStandaloneTrueOrderByDisplayOrderAsc();    }

}
