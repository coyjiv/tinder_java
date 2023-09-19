package dao;

import domain.Reaction;

import java.util.List;
import java.util.Optional;

public interface ReactionDao {
    boolean create(Reaction reaction);
    Optional<Reaction> findById(Long reactionId);
    List<Reaction> findByUserId(Long userId);
    boolean delete(Long reactionId);
}
