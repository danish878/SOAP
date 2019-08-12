package org.danny.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.danny.messenger.database.Database;
import org.danny.messenger.model.Profile;

public class ProfileService {

    private Map<String, Profile> profiles = Database.getProfiles();

    public ProfileService() {
        profiles.put("Danny", new Profile(1L, "Danish", "Daood", "Danny"));
        profiles.put("Jimmy", new Profile(2L, "Saleem", "Bhai", "Jimmy"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

}
