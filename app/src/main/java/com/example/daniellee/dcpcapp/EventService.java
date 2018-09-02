package com.example.daniellee.dcpcapp;

import java.util.List;
//import nz.net.crane.dcbc.events.Event;
//import nz.net.crane.dcbc.events.User;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Paul Crane on 1/09/18.
 *
 * @author Paul Crane <paul@crane.net.nz>
 */
public interface EventService {

	//@GET("users/")
	//Call<List<User>> listUsers();

	@GET("events/")
	Call<List<Event>> listEvents();

}