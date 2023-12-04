package com.standup.controller;

import Exceptions.TeamSpaceNotPresent;
import com.standup.model.TeamSpace;
import com.standup.service.TeamSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teamspace")
public class TeamSpaceController {

    @Autowired
    private TeamSpaceService teamSpaceService;


    @PostMapping("/create")
    public TeamSpace createTeamSpace(@RequestBody TeamSpace teamSpace) throws TeamSpaceNotPresent {
        return teamSpaceService.createTeamSpace(teamSpace);
    }

    @GetMapping("/get")
    public List<TeamSpace> getAllTeamSpace(){
        return  teamSpaceService.getAllTeamSpace();
    }

    @DeleteMapping("/delete/{title}/{id}")
    public String deleteByTitle(@PathVariable String title,@PathVariable Long id) throws TeamSpaceNotPresent {
        return teamSpaceService.deleteNamespaceByTitle(title,id);
    }
}
