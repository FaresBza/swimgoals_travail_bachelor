package com.swimgoals.service;

import java.util.List;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.models.Group;

public interface GroupService {
    
    List<Group> getAllGroups();

    List<Group> getGroupsByCoachId(int coachId);
    
    Group createGroup(GroupDTO groupDTO);

    void joinGroup(int swimmerId, int groupId);
}
