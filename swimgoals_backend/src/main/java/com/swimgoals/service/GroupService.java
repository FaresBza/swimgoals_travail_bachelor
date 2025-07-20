package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.models.Group;

public interface GroupService {
    
    /**
     * Retrieves a list of all groups
     * 
     * @return a list of Group objects representing all groups
     */
    List<Group> getAllGroups();

    /**
     * Retrieves a list of groups associated with a specific coach
     * 
     * @param coachId the unique identifier of the coach whose groups are to be retrieved
     * @return a list of Group objects representing the groups managed by the specified coach
     */
    List<Group> getGroupsByCoachId(int coachId);
    
    /**
     * Creates a new group based on the provided GroupDTO object
     * 
     * @param groupDTO the data transfer object containing the details of the group
     * @return the newly created Group object
     */
    Group createGroup(GroupDTO groupDTO);

    /**
     * Adds a swimmer to a specific group
     * 
     * @param swimmerId the unique identifier of the swimmer to be added to the group
     * @param groupId the unique identifier of the group to which the swimmer will be added
     */
    void joinGroup(int swimmerId, int groupId);

    /**
     * Retrieves a group by its unique identifier.
     * 
     * @param id the unique identifier of the group to be retrieved
     * @return an Optional containing the Group object if found
     */
    public Optional<Group> getGroupById(Integer id);
}
