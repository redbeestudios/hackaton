package com.redbee.io.converter;

import com.redbee.io.persistence.entities.Event;
import com.redbee.io.persistence.entities.Vote;
import com.redbee.io.persistence.repositories.VoteRepository;
import com.redbee.io.representation.EventRepresentation;
import com.redbee.io.representation.VoteRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fabrizio on 21/10/16.
 */
@Component
public class VoteConverter {

    public VoteRepresentation convert(Vote vote){
        VoteRepresentation result = new VoteRepresentation();
        result.setId(vote.getId());
        result.setRestaurant(vote.getRestaurant());
        result.setUser(vote.getUser());
        return result;
    }

    public Vote convertRepresentation(VoteRepresentation voteRepresentation) {
        Vote result = new Vote();
        result.setId(voteRepresentation.getId());
        result.setUser(voteRepresentation.getUser());
        result.setRestaurant(voteRepresentation.getRestaurant());
        return result;
    }

    public List<VoteRepresentation> convertList(List<Vote> votes) {
        if(votes != null){
            return votes.stream().map(this::convert).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public List<Vote> convertListRepresentation(List<VoteRepresentation> votes) {
        return votes.stream().map(this::convertRepresentation).collect(Collectors.toList());
    }
}