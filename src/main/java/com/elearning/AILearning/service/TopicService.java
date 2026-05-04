package com.elearning.AILearning.service;

import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.repository.TopicRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRespository topicRepo;

    public List<Topic> getTopicList(){
        return topicRepo.findAllByIsPublishedTrueOrderByDisplayOrderAsc();    }

}
