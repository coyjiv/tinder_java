package service;

import dao.ReactionDao;
import domain.Reaction;

import java.util.List;

public class JdbcReactionService implements ReactionService{
    private final ReactionDao reactionDao;
    public JdbcReactionService(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    @Override
    public boolean create(Reaction reaction) {
        return reactionDao.create(reaction);
    }

    @Override
    public List<Reaction> findByUserId(Long userId) {
        return reactionDao.findByUserId(userId);
    }

    @Override
    public boolean delete(Long reactionId) {
        return reactionDao.delete(reactionId);
    }

    @Override
    public boolean likeUser(Long userId, Long targetUserId) {
        Reaction reaction = new Reaction(null, userId, targetUserId, "like", null);
        return reactionDao.create(reaction);
    }

    @Override
    public boolean dislikeUser(Long userId, Long targetUserId) {
        Reaction reaction = new Reaction(null, userId, targetUserId, "dislike", null);
        return reactionDao.create(reaction);
    }
}
