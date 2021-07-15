package com.andreygrosbelli.workshopmongo.resources;

import com.andreygrosbelli.workshopmongo.domain.Post;
import com.andreygrosbelli.workshopmongo.domain.User;
import com.andreygrosbelli.workshopmongo.dto.UserDTO;
import com.andreygrosbelli.workshopmongo.resources.util.URL;
import com.andreygrosbelli.workshopmongo.services.PostService;
import com.andreygrosbelli.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

   /* @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }*/

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="search", defaultValue = "") String search,
            @RequestParam(value="minDate", defaultValue = "") String minDate,
            @RequestParam(value="maxDate", defaultValue = "") String maxDate,
            @RequestParam(value="text", defaultValue = "") String text
            ) {
        search = URL.decodeParam(search);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        text = URL.decodeParam(text);
        if(!search.equals("")){
            List<Post> list = service.fullSearch(search, min, max);
            return ResponseEntity.ok().body(list);

        } if (!text.equals("")) {
            List<Post> list = service.findByTitle(text);
            return ResponseEntity.ok().body(list);
        } else {
            List<Post> list = service.findAll();
            return ResponseEntity.ok().body(list);
        }
    }
}
