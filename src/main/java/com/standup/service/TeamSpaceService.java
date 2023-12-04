package com.standup.service;

import Exceptions.TeamSpaceNotPresent;
import com.standup.model.TeamSpace;
import com.standup.model.User;
import com.standup.repo.TeamSpaceRepository;
import com.standup.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamSpaceService {

    @Autowired
    private TeamSpaceRepository teamSpaceRepository;

    @Autowired
    private UserRepository userRepository;
    public TeamSpace createTeamSpace(TeamSpace teamSpaceRequest) throws TeamSpaceNotPresent {
        if(teamSpaceRepository.findByTitle(teamSpaceRequest.getTitle()).isPresent()) {
            throw new TeamSpaceNotPresent("TeamSpace with" + teamSpaceRequest.getTitle() +" title is present");
        }
        Set<User> userIds = teamSpaceRequest.getUsers();
        Set<User> findUserByIds = userIds.stream()
                .map(user ->{
                    return userRepository.findById(user.getId()).get();
                }).collect(Collectors.toSet());

        teamSpaceRequest.setUsers(findUserByIds);
        TeamSpace teamSpaceResponse = teamSpaceRepository.save(teamSpaceRequest);

        findUserByIds = findUserByIds.stream().map(user ->{
            user.getTeamSpaces().add(teamSpaceResponse);
            return user;
        }).collect(Collectors.toSet());

        userRepository.saveAll(findUserByIds);
        return teamSpaceResponse;
    }

    public String deleteNamespaceByTitle(String title,Long id) throws TeamSpaceNotPresent {

//        if(teamSpaceRepository.findByTitle(title).isEmpty()) {
//            throw new TeamSpaceNotPresent("TeamSpace with this title is not present");
//        }

//        teamSpaceRepository.deleteByTitle(title);
            teamSpaceRepository.deleteById(id);
        return "successfully deleted TeamSpace : "+title;
    }

    public List<TeamSpace> getAllTeamSpace(){
        List<TeamSpace> teamSpaces = teamSpaceRepository.findAll();
        return teamSpaces;
    }

    public TeamSpace getByTitle(String title) throws TeamSpaceNotPresent {
        if(teamSpaceRepository.findByTitle(title).isEmpty()) {
            throw new TeamSpaceNotPresent("TeamSpace with this title is not present");
        }
        return teamSpaceRepository.findByTitle(title).get();
    }

//    public TeamSpace addUsers(List<>){
//
//    }
}
