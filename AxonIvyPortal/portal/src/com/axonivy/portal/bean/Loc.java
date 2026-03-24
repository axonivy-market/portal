package com.axonivy.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.SecurityMemberDTO;
import com.axonivy.portal.components.dto.UserDTO;
import com.axonivy.portal.components.service.IvyAdapterService;
import com.axonivy.portal.components.util.CustomProcessUtils;
import com.axonivy.portal.enums.PortalCustomSignature;

import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.workflow.ITask;

public class Loc implements Serializable{

    private static final long serialVersionUID = 7350665702475663264L;

    // // A simple wrapper object to return both lists together
    // public static class DelegationData {
    //     private List<UserDTO> users = new ArrayList<>();
    //     private List<RoleDTO> roles = new ArrayList<>();

    //     public List<UserDTO> getUsers() { return users; }
    //     public List<RoleDTO> getRoles() { return roles; }
    // }

        // ... [keep your existing getCustomDelegates method here] ...
    /**
     * Autocomplete method for Bulk Delegation.
     * Computes the intersection of users over multiple tasks, filters by search query, and paginates.
     */
    public static List<UserDTO> getBulkDelegateAutocomplete(
            List<RoleDTO> roles,
            List<UserDTO> users,
            List<ITask> selectedTasks,
            SecurityMemberDTO currentUserName) // Matches your existing portal exclude logic
    {
        if (selectedTasks == null || selectedTasks.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> intersectedUsernames = null;
        Map<String, IUser> userCache = new HashMap<>(); // Caches DB lookups
        
        // ==========================================================
        // STEP 1: CALCULATE THE INTERSECTION (The Performance Test)
        // ==========================================================
        for (ITask task : selectedTasks) {
            
            // Call our method we built earlier to get Candidate Users & Roles for this task
            Map<String, Object> params = new HashMap<>();
            params.put("roles", roles);
            params.put("users", users);
            params.put("currentUser", currentUserName);
            params.put("task", task);
            List<Map<String, Object>> result = IvyAdapterService.startSubProcessesInSecurityContext(PortalCustomSignature.DELEGATE.getSignature(), params);
            List<RoleDTO> customRoles = new ArrayList<>();
            List<UserDTO> customUsers = new ArrayList<>();

            for (Map<String, Object> map : (List<Map<String, Object>>)  result) {
                if (!CustomProcessUtils.isSkipCustomProcess(map)) {
                    Object rolesObj = map.get("roles");
                    if (rolesObj instanceof List) {
                        customRoles.addAll((List<RoleDTO>) rolesObj);
                    }
                    Object usersObj = map.get("users");
                    if (usersObj instanceof List) {
                        customUsers.addAll((List<UserDTO>) usersObj);
                    }
                }
            }

            // in.roles = RoleUtils.distinctAndSortRoleList(customRoles);
            // in.users = UserUtils.distinctAndSortUserList(customUsers);

            // DelegationData data = getCustomDelegates(task.getName(), currentUserName);
            Set<String> currentTaskUsernames = new HashSet<>();
            
            // Add explicitly mapped users
            for (UserDTO userDto : customUsers) {
                currentTaskUsernames.add(userDto.getName()); 
            }
            
            // Unwrap and add all users belonging to mapped roles
            for (RoleDTO roleDto : customRoles) {
                IRole role = ch.ivyteam.ivy.environment.Ivy.security().roles().find(roleDto.getName());
                if (role != null) {  
                    for (IUser u : role.users().allPaged()) {
                        currentTaskUsernames.add(u.getName());
                        userCache.put(u.getName(), u); // Cache for later
                    }
                }
            }
            // Perform the Intersection
            if (intersectedUsernames == null) {
                // Initialize the base group with the first task
                intersectedUsernames = currentTaskUsernames;
            } else {
                // Keep ONLY users that currently exist in both sets (The intersection!)
                intersectedUsernames.retainAll(currentTaskUsernames);
            }
            // EARLY EXIT: If intersection hits 0 (Test Case 3.2), no one can do all tasks
            if (intersectedUsernames.isEmpty()) {
                return new ArrayList<>();
            }
        }
        // ==========================================================
        // STEP 2: APPLY AUTOCOMPLETE QUERY & EXCLUSION LOGIC
        // ==========================================================
        // List<IUser> filteredUsers = new ArrayList<>();
        // String lowerQuery = (query == null) ? "" : query.toLowerCase();
        // for (String username : intersectedUsernames) {
            
        //     // Respect excluded usernames
        //     if (excludedUsernames != null && excludedUsernames.contains(username)) {
        //         continue;
        //     }
        //     // Retrieve the IUser object (Hits cache first, DB fallback)
        //     IUser user = userCache.get(username);
        //     if (user == null) {
        //         user = ch.ivyteam.ivy.environment.Ivy.security().users().find(username);
        //     }
        //     if (user != null) {
        //         // Mimic the "findUsers" autocomplete by checking Username or Full Name
        //         String name = user.getName() != null ? user.getName().toLowerCase() : "";
        //         String fullName = user.getFullName() != null ? user.getFullName().toLowerCase() : "";
                
        //         if (name.contains(lowerQuery) || fullName.contains(lowerQuery)) {
        //             filteredUsers.add(user);
        //         }
        //     }
        // }
        // ==========================================================
        // STEP 3: APPLY PAGINATION (startIndex and count)
        // ==========================================================
        // List<UserDTO> finalResult = new ArrayList<>();
        
        // // Prevent ArrayOutOfBounds if there are fewer results than 'count'
        // int maxIndex = Math.min(startIndex + count, filteredUsers.size());
        
        for (int i = startIndex; i < maxIndex; i++) {
            finalResult.add(new UserDTO(filteredUsers.get(i)));
        }
        return finalResult;
    }

}
