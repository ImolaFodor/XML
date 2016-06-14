package xml.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import security.PasswordStorage;
import xml.Constants;
import xml.model.Korisnik;
import xml.repositories.IUserDAO;
import xml.stateStuff.State;
import xml.stateStuff.StateManager;

@RestController
@RequestMapping("/korisnik")
public class UserController {

	@Autowired
	private IUserDAO userDao;

	@RequestMapping(value = "/svi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Korisnik>> getAll() {
		try {
			List<Korisnik> korisnici = userDao.getAll();
			if (korisnici == null)
				return new ResponseEntity<List<Korisnik>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<List<Korisnik>>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public ResponseEntity post() {
		System.out.println("DODAJ");
		Korisnik k1 = new Korisnik();
		k1.setIme("Gavrilo");
		k1.setPrezime("Drljaca");
		k1.setEmail("gavrilo@gavrilo");
		k1.setPassword("gavrilo");
		k1.setUsername("gavrilo");
		k1.setUloga("Odbornik");
		k1.setSalt("DASDA");
		k1.setId((long) 1);
		try {
			userDao.create(k1, Constants.User + k1.getId().toString(), Constants.UsersCollection);
		} catch (JAXBException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}

		k1.setIme("Imola");
		k1.setPrezime("Fodor");
		k1.setEmail("imola@imola");
		k1.setPassword("imola");
		k1.setUsername("imola");
		k1.setUloga("Odbornik");
		k1.setSalt("DASDA");
		k1.setId((long) 2);
		try {
			userDao.create(k1, Constants.User + k1.getId().toString(), Constants.UsersCollection);
		} catch (JAXBException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}
		k1.setIme("Milos");
		k1.setPrezime("Sidji");
		k1.setEmail("milos@milos");
		k1.setPassword("milos");
		k1.setUsername("milos");
		k1.setSalt("DASDA");
		k1.setUloga("Predsednik");
		k1.setId((long) 3);
		try {
			userDao.create(k1, Constants.User + k1.getId().toString(), Constants.UsersCollection);
		} catch (JAXBException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}

		return new ResponseEntity(HttpStatus.OK);

		/*
		 * try { userDao.create(korisnik, Constants.User +
		 * korisnik.getId().toString(),Constants.UsersCollection); return new
		 * ResponseEntity(HttpStatus.OK); } catch (IOException e) {
		 * System.out.println(e.toString()); return new
		 * ResponseEntity(HttpStatus.BAD_REQUEST); } catch (JAXBException e) {
		 * System.out.println(e.toString()); return new
		 * ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT); }
		 */
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> getById(@PathVariable("id") Long id) {

		try {
			Korisnik user = null;
			try {
				user = userDao.get(id);
			} catch (JAXBException e) {
				return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
			}
			if (user == null) {
				return new ResponseEntity<Korisnik>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<Korisnik>(user, HttpStatus.OK);
			}
		} catch (IOException e) {
			return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> getByLogin(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		
		System.out.println("login");
		try {
			Korisnik user = userDao.getByLogin(username, password);
			if (user == null) {
				return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
			} else {
				final Collection<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(user.getUloga()));
				final Authentication authentication = new PreAuthenticatedAuthenticationToken(user, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				return new ResponseEntity<Korisnik>(user, HttpStatus.OK);
			}
		} catch (IOException e) {

			return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
		} catch (JAXBException e) {
			return new ResponseEntity<Korisnik>(HttpStatus.BAD_REQUEST);
		}
	}

	// for presidend
	@RequestMapping(value = "/state", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getState() {
		State state = StateManager.getState();
		if (state == null)
			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		return new ResponseEntity(state, HttpStatus.OK);
	}

	@RequestMapping(value = "/state", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity setState(@RequestBody State state) {

		if (StateManager.setState(state.getState()))
			return new ResponseEntity(state, HttpStatus.OK);

		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/me")
	public ResponseEntity getProfile() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken) ){
			return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
		}else{
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

}
