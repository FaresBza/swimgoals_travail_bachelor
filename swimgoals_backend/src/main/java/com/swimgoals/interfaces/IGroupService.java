package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.models.Group;

public interface IGroupService {
    
    List<Group> getAllGroups();
    
    Group createGroup(GroupDTO groupDTO);
}
