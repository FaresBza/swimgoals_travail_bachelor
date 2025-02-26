package com.swimgoals.interfaces;

import java.util.List;

import com.swimgoals.models.Group;
import com.swimgoals.models.User;

public interface IGroupService {

    List<Group> getAllGroups();

    List<Group> getAllGroupsByCoachId(User coach);

    Group createGroup(Group group);

    Group updateGroup(Integer groupId, Group group);

    void deleteGroup(Integer groupId);
}
