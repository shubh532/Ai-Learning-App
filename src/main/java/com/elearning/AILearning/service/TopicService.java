package com.elearning.AILearning.service;

import com.elearning.AILearning.entity.Topic;
import com.elearning.AILearning.entity.User;
import com.elearning.AILearning.entity.UserTopicProgress;
import com.elearning.AILearning.enums.LearnState;
import com.elearning.AILearning.enums.MasteryState;
import com.elearning.AILearning.enums.TopicType;
import com.elearning.AILearning.exception.ResourceNotFoundException;
import com.elearning.AILearning.repository.TopicRepository;
import com.elearning.AILearning.repository.UserProfileRepository;
import com.elearning.AILearning.repository.UserTopicProgressRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class TopicService {
    @PersistenceContext
    private EntityManager entityManager;
    private final TopicRepository topicRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserTopicProgressRepository userTopicProgressRepository;

    public List<Topic> getTopicList() {
        return topicRepository.findByIsPublishedTrueAndIsStandaloneTrueOrderByDisplayOrderAsc();
    }

    @Transactional
    public void enrollTopic(UUID userId, List<UUID> topicIds) {

        List<Topic> selectedTopics = topicRepository.findAllById(topicIds);

        if (selectedTopics.size() != topicIds.size()) {
            throw new ResourceNotFoundException("Some topics not found");
        }

        List<UUID> categoryIds = selectedTopics.stream()
                .filter(t -> t.getTopicType() == TopicType.CATEGORY)
                .map(Topic::getId)
                .toList();

        List<Topic> childTopics = categoryIds.isEmpty()
                ? List.of()
                : topicRepository.findByParentIdInOrderByDisplayOrderAsc(categoryIds);

        List<Topic> allTopics = Stream.concat(
                selectedTopics.stream(),
                childTopics.stream()
        ).distinct().toList();

        Set<UUID> allTopicIds = allTopics.stream()
                .map(Topic::getId)
                .collect(Collectors.toSet());

        Set<UUID> existingTopicIds = userProfileRepository
                .findExistingTopicIds(userId, allTopicIds);

        List<UserTopicProgress> newProgressList = new ArrayList<>();


        User userRef = entityManager.getReference(User.class, userId);

        for (Topic topic : allTopics) {
            if (!existingTopicIds.contains(topic.getId())) {
                UserTopicProgress progress = new UserTopicProgress();
                progress.setTopic(topic);
                progress.setUser(userRef);
                progress.setLearnState(LearnState.NOT_STARTED);
                progress.setMasteryState(MasteryState.NOT_STARTED);
                progress.setLastVisitedAt(LocalDateTime.now());

                newProgressList.add(progress);
            }
        }
        if (!newProgressList.isEmpty()) {
            userTopicProgressRepository.saveAll(newProgressList);
        }
        updateOnboardingStatus(userId);
    }

    private void updateOnboardingStatus(UUID userId) {
        userProfileRepository.findById(userId).ifPresent(profile -> {
            if (!profile.isOnboardingDone()) {
                profile.setOnboardingDone(true);
                userProfileRepository.save(profile);
            }
        });
    }
}
