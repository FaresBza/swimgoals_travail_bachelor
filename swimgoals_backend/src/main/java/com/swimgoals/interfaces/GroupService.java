package com.swimgoals.interfaces;

import java.util.List;
import java.util.UUID;

import com.swimgoals.models.Group;

public interface GroupService {

    List<Group> getAllGroups();

    List<Group> getAllGroupsByCoachId(UUID coachId);

    Group createGroup(Group group);

    Group updateGroup(Integer groupId, Group group);

    void deleteGroup(Integer groupId);
}
