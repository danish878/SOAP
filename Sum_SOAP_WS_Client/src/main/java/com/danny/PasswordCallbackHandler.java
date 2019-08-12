package com.danny;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

public class PasswordCallbackHandler implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<>(); // In memory database for credentials

    public PasswordCallbackHandler() {
        passwords.put("danny", "danny");
        passwords.put("myclientkey", "ckpass");
    }

    @Override
    public void handle(Callback[] callbacks) {
        for (Callback callback : callbacks) {
            WSPasswordCallback pc = (WSPasswordCallback) callback;
            String password = passwords.get(pc.getIdentifier());
            pc.setPassword(password);
            return;
        }
    }
}
