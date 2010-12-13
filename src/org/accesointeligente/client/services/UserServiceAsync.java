package org.accesointeligente.client.services;

import org.accesointeligente.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void login(String email, String password, AsyncCallback<User> callback);
	void register(User user, AsyncCallback<User> callback);
}