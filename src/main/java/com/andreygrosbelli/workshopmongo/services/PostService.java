package com.andreygrosbelli.workshopmongo.services;

import com.andreygrosbelli.workshopmongo.domain.Post;
import com.andreygrosbelli.workshopmongo.repositories.PostRepository;
import com.andreygrosbelli.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}