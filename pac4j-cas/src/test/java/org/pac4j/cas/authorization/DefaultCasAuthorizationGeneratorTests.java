package org.pac4j.cas.authorization;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.ProfileHelper;

import static org.junit.Assert.*;

/**
 * This class tests the {@link DefaultCasAuthorizationGenerator}.
 * 
 * @author Michael Remond
 * @since 1.5.1
 */
public final class DefaultCasAuthorizationGeneratorTests {

    @Test
    public void testNoAttribute() {
        AuthorizationGenerator<CommonProfile> generator = new DefaultCasAuthorizationGenerator<>();
        Map<String, Object> attributes = new HashMap<>();
        CommonProfile profile = ProfileHelper.buildProfile("org.pac4j.cas.profile.CasProfile#id", attributes);
        generator.generate(profile);
        assertEquals(false, profile.isRemembered());
    }

    @Test
    public void testBadAttributeValue() {
        AuthorizationGenerator<CommonProfile> generator = new DefaultCasAuthorizationGenerator<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(DefaultCasAuthorizationGenerator.DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME, "yes");
        CommonProfile profile = ProfileHelper.buildProfile("org.pac4j.cas.profile.CasProfile#id", attributes);
        generator.generate(profile);
        assertEquals(false, profile.isRemembered());
    }

    @Test
    public void testIsNotRemembered() {
        AuthorizationGenerator<CommonProfile> generator = new DefaultCasAuthorizationGenerator<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(DefaultCasAuthorizationGenerator.DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME, "false");
        CommonProfile profile = ProfileHelper.buildProfile("org.pac4j.cas.profile.CasProfile#id", attributes);
        generator.generate(profile);
        assertEquals(false, profile.isRemembered());
    }

    @Test
    public void testIsRemembered() {
        AuthorizationGenerator<CommonProfile> generator = new DefaultCasAuthorizationGenerator<>();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(DefaultCasAuthorizationGenerator.DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME, "true");
        CommonProfile profile = ProfileHelper.buildProfile("org.pac4j.cas.profile.CasProfile#id", attributes);
        generator.generate(profile);
        assertEquals(true, profile.isRemembered());
    }
}
