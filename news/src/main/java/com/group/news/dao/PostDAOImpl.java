package com.group.news.dao;

import com.group.news.entity.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO{
    private EntityManager entityManager;

    @Autowired
    public PostDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }


    @Override
    public List<Post> getPostsFromDb(int pageNumber) {
        TypedQuery<Post> theQuery = entityManager.createQuery("from Post ORDER BY " +
                "amountLike DESC", Post.class)
                .setFirstResult((pageNumber - 1) * 5)
                .setMaxResults(5);

        List<Post> posts = theQuery.getResultList();

        return posts;
    }
}
