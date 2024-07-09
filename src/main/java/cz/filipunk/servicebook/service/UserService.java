package cz.filipunk.servicebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cz.filipunk.servicebook.entity.Users;
import cz.filipunk.servicebook.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<Users> findAll() {
		return userRepository.findAll();
	}

	public void save(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void updateUser(Users user) {
		// Načteme původního uživatele z databáze
		Users existingUser = userRepository.findById(user.getId()).orElse(null);
		if (existingUser != null) {
			// Aktualizujeme pouze změněné údaje
			existingUser.setUsername(user.getUsername());
			existingUser.setEmail(user.getEmail());
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				// Pokud je heslo změněno, zašifrujeme nové heslo
				existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			userRepository.save(existingUser);
		}
	}
}
