package com.opdracht1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class JDBCClientDao {
	private static Map<Integer, Client> clients = new HashMap<>();

	public Collection<Client> findAllClients() {
		return clients.values();
	}

	public Client findClientById(Client client) {
		return clients.get(client.getId());
	}

	public void create(Client client) {
		clients.put(client.getId(), client);
	}

	public void delete(Client client) {
		clients.remove(client.getId());
	}

	public void update(Client client) {

	}
}