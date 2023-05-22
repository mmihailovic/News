package com.example.webprojekatjun.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.webprojekatjun.entities.User;
import com.example.webprojekatjun.repositories.UserRepository;
import com.example.webprojekatjun.resources.UserResource;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import java.util.List;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    public String login(String username, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(username);
        if (user == null || !user.getHashedPassword().equals(hashedPassword) || !user.isAktivan()) {
            return null;
        }

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withSubject(username)
                .withClaim("tip",user.getTip())
                .sign(algorithm);
    }

    public User register(String username, String password, String ime, String prezime, String tip, boolean aktivan) {
        User u = this.userRepository.findUser(username);
        if (u != null) {
            return null;
        }
        String hashedPassword = DigestUtils.sha256Hex(password);
        User user = new User(username,hashedPassword,ime,prezime,tip,aktivan);
        return userRepository.addUser(user);
    }

    public boolean updateUser(String username, String newUsername, String ime, String prezime, String tip) {
        return userRepository.updateUser(username,newUsername,ime,prezime,tip);
    }
    public boolean setStatus(String username, boolean aktivan) {
        User u = this.userRepository.findUser(username);
        if (u == null) {
            return false;
        }
        userRepository.setStatus(username,aktivan);
        return true;
    }

    public boolean isAuthorized(String token, ContainerRequestContext requestContext){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
        String tip = jwt.getClaim("tip").asString();
//        jwt.getClaim("role").asString();

        User user = this.userRepository.findUser(username);

        if (user == null){
            return false;
        }

        if(!user.isAktivan())
            return false;

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof UserResource && tip.equals("CONTENT_CREATOR")) {
                return false;
            }
        }

        return true;
    }
}
