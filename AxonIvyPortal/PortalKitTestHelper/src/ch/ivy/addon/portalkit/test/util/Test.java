package ch.ivy.addon.portalkit.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.ISecurityContext;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.role.NewRole;
import ch.ivyteam.ivy.security.user.NewUser;

public class Test {

    public Test() {
        
    }

    public static void test1() {
        // ==========================================
        // 1. DEFINE ROLE NAMES FOR TEST CASES
        // ==========================================
        String[] avgRolesNames = { "TC_AVG_1", "TC_AVG_2", "TC_AVG_3", "TC_AVG_4", "TC_AVG_5" };
        String[] largeRolesNames = { "TC_LARGE_1", "TC_LARGE_2", "TC_LARGE_3", "TC_LARGE_4", "TC_LARGE_5" };
        String[] overlapRolesNames = { "TC_HIGH_OVERLAP_1", "TC_HIGH_OVERLAP_2", "TC_HIGH_OVERLAP_3" };
        String[] noOverlapRolesNames = { "TC_NO_OVERLAP_1", "TC_NO_OVERLAP_2" };
        // ==========================================
        // 2. CLEAN UP OLD TEST ROLES (Optional)
        // ==========================================
        List<String[]> allRoleGroups = Arrays.asList(avgRolesNames, largeRolesNames, overlapRolesNames,
                noOverlapRolesNames);
        for (String[] roleGroup : allRoleGroups) {
            for (String roleName : roleGroup) {
                if (Ivy.security().roles().find(roleName) != null) {
                    Ivy.security().roles().delete(roleName);
                }
            }
        }
        // ==========================================
        // 3. CREATE NEW TEST ROLES
        // ==========================================
        List<IRole> avgRoles = new ArrayList<>();
        List<IRole> largeRoles = new ArrayList<>();
        List<IRole> overlapRoles = new ArrayList<>();
        List<IRole> noOverlapRoles = new ArrayList<>();
        for (String name : avgRolesNames) {
            avgRoles.add(Ivy.security().roles().create(NewRole.create(name).displayName(name).toNewRole()));
        }
        for (String name : largeRolesNames) {
            largeRoles.add(Ivy.security().roles().create(NewRole.create(name).displayName(name).toNewRole()));
        }
        for (String name : overlapRolesNames) {
            overlapRoles.add(Ivy.security().roles().create(NewRole.create(name).displayName(name).toNewRole()));
        }
        for (String name : noOverlapRolesNames) {
            noOverlapRoles.add(Ivy.security().roles().create(NewRole.create(name).displayName(name).toNewRole()));
        }
        // ==========================================
        // 4. GENERATE 10,000 USERS AND ASSIGN THEM
        // ==========================================
        int totalUsers = 10000;
        for (int i = 0; i < totalUsers; i++) {
            String username = "perf_testuser_" + i;
            IUser user = ISecurityContext.current().users().find(username);

            // Create user if not exists
            if (user == null) {
                NewUser newUser = NewUser.create(username)
                        .fullName("Perf Test User " + i)
                        .password("1")
                        .language(java.util.Locale.ENGLISH)
                        .toNewUser();
                user = ISecurityContext.current().users().create(newUser);
            }
            // ---------------------------------------------------------
            // SCENARIO 1: "Average" Roles (~500 users each, slight overlap)
            // ---------------------------------------------------------
            if (i >= 0 && i < 500)
                user.addRole(avgRoles.get(0));
            if (i >= 250 && i < 750)
                user.addRole(avgRoles.get(1)); // 250 users overlap with AVG_1
            if (i >= 500 && i < 1000)
                user.addRole(avgRoles.get(2));
            if (i >= 750 && i < 1250)
                user.addRole(avgRoles.get(3));
            if (i >= 1000 && i < 1500)
                user.addRole(avgRoles.get(4));
            // ---------------------------------------------------------
            // SCENARIO 2: "Large Volume" Roles (~9000 users each, high memory load)
            // ---------------------------------------------------------
            if (i < 9000)
                user.addRole(largeRoles.get(0));
            if (i >= 500 && i < 9500)
                user.addRole(largeRoles.get(1));
            if (i >= 1000)
                user.addRole(largeRoles.get(2));
            if (i < 8000 || i >= 9000)
                user.addRole(largeRoles.get(3));
            if (i >= 200 && i < 9200)
                user.addRole(largeRoles.get(4));
            // ---------------------------------------------------------
            // SCENARIO 3: "High Overlap" Roles (All have the EXACT same 5000 users)
            // ---------------------------------------------------------
            if (i >= 2000 && i < 7000) {
                for (IRole role : overlapRoles) {
                    user.addRole(role);
                }
            }
            // ---------------------------------------------------------
            // SCENARIO 4: "No Overlap" Roles (Mutually exclusive users)
            // ---------------------------------------------------------
            if (i >= 0 && i < 1000)
                user.addRole(noOverlapRoles.get(0));
            if (i >= 1000 && i < 2000)
                user.addRole(noOverlapRoles.get(1)); // Zero users overlap with NO_OVERLAP_1
        }

    }
}
