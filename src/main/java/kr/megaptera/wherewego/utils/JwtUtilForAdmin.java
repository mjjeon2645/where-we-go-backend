package kr.megaptera.wherewego.utils;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;

public class JwtUtilForAdmin {
    private final Algorithm algorithm;

    public JwtUtilForAdmin(String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String encode(String adminId) {
        return JWT.create()
            .withClaim("adminId", adminId)
            .sign(algorithm);
    }

    public String decode(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT verify = verifier.verify(token);
        return verify.getClaim("adminId").asString();
    }
}
