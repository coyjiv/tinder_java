package service;

import domain.Reaction;

import java.util.List;

public interface ReactionService {

    boolean create(Reaction reaction);
    List<Reaction> findByUserId(Long userId);
    boolean delete(Long reactionId);

    boolean likeUser(Long userId, Long targetUserId);
    boolean dislikeUser(Long userId, Long targetUserId);
}
