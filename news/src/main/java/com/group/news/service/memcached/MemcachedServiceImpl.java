package com.group.news.service.memcached;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.news.entity.Post;
import com.group.news.entity.Title;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemcachedServiceImpl implements MemcachedService{

    @Value("${memcached.cache.server}")
    private String serverIp;

    @Value("${memcached.cache.port}")
    private int port;

    @Override
    public List<Post> getPostsFromMemcached(int pageNumber) {
        int postPerPage = 5;
        int offset = (pageNumber - 1) * postPerPage;
        MemcachedClient mcc;
        try {
            mcc = new MemcachedClient(new InetSocketAddress(serverIp, port));
            System.out.println("connect memcached successfully");

            List<Post> posts = new ArrayList<>();
            for (int i = 0; i < postPerPage; i++) {
                String key = "post" + String.valueOf(i + offset);
                if (mcc.get(key) == null) {
                    break;
                }
                ObjectMapper objectMapper = new ObjectMapper ();
                String dataJson = (String) mcc.get(key);
                MemcachedPost data = objectMapper.readValue(dataJson, MemcachedPost.class);

                Title theTitle = new Title(data.getTitle().getTitleId(), data.getTitle().getGenre());
                Post thePost = new Post(data.getId(), data.getContent(), data.getAmountLike(), theTitle);
                posts.add(thePost);
            }
            return posts;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
