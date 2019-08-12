package com.danny.sumws;

import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import java.util.HashMap;
import java.util.Map;

public class PasswordCallbackHandler implements CallbackHandler {

    private Map<String, String> passwords = new HashMap<>();

    public PasswordCallbackHandler() {
        passwords.put("danny", "danny");
        passwords.put("myservicekey", "skpass");
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
