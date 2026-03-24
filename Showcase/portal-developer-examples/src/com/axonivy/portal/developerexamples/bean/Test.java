package com.axonivy.portal.developerexamples.bean;

import java.util.ArrayList;
import java.util.List;

import com.axonivy.portal.components.dto.RoleDTO;
import com.axonivy.portal.components.dto.UserDTO;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;

// IMPORTANT: Make sure to add your imports for UserDTO and RoleDTO here
// import com.yourpackage.UserDTO;
// import com.yourpackage.RoleDTO;

public class Test {

    // A simple wrapper object to return both lists together
    public static class DelegationData {
        private List<UserDTO> users = new ArrayList<>();
        private List<RoleDTO> roles = new ArrayList<>();

        public List<UserDTO> getUsers() { return users; }
        public List<RoleDTO> getRoles() { return roles; }
    }

    public Test() {
    }

    /**
     * Resolves the list of custom delegates (users and roles) based on the task.
     * 
     * @param taskName The name of the current task
     * @param currentUserName The name of the session user running the process
     * @return DelegationData containing the users and roles allowed for this task
     */
    public DelegationData getCustomDelegates(String taskName, String currentUserName) {
        DelegationData data = new DelegationData();

        // 1. Guest check
        if ("#guest".equalsIgnoreCase(currentUserName)) {
            return data; // Returns empty data
        }

        // 2. Performance test check (Dynamic mapping)
        // If task is named "TC_LARGE_1", it finds the role "TC_LARGE_1" from Ivy DB
        IRole testRole = Ivy.security().roles().find(taskName);
        
        if (testRole != null && taskName.startsWith("TC_")) {
            // Found a performance test match. Load all users inside this role.
            for (IUser u : testRole.users().allPaged()) {
                data.getUsers().add(new UserDTO(u));
            }
            
            // If you also need to populate roles list for testing, you could do:
            // data.getRoles().add(new RoleDTO(testRole));
            
            return data;
        }

        // 3. Your standard existing logic (Fallback for general dev)
        if ("A".equalsIgnoreCase(taskName) || "B".equalsIgnoreCase(taskName)) {
            IUser demoUser = Ivy.security().users().find("demo");
            if (demoUser != null) {
                data.getUsers().add(new UserDTO(demoUser));
            }
        }

        return data;
    }

}
