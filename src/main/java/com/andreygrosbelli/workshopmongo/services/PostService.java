package com.andreygrosbelli.workshopmongo.services;

import com.andreygrosbelli.workshopmongo.domain.Post;
import com.andreygrosbelli.workshopmongo.domain.User;
import com.andreygrosbelli.workshopmongo.repositories.PostRepository;
import com.andreygrosbelli.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> findAll() {
        return repo.findAll();
    }

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repo.searchTitle(text);
    }

    public List<Post> filters(String search, String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

        if(!text.equals("")) {
            return repo.searchTitle(text);
        } if (!search.equals("")) {
            return repo.fullSearch(search, minDate, maxDate);
        } else {
            return repo.findAll();
        }
    }
}
