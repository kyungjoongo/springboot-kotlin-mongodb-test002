package com.kyungjoon.utils;


import com.kyungjoon.user.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;

import static com.kyungjoon.constants.Constants.jwt_secret;


@Component
public class JwtUtil {


    @Value(jwt_secret)
    private String SECRET_KEY;
    private String DATA_KEY = "userId";
    public final static long TOKEN_VALIDATION_SECOND = 1000L * 3600 * 24 * 1;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 3600 * 24 * 1;

    //토큰 생성
    public String createToken(String userId) {
        return doCreateToken(userId, TOKEN_VALIDATION_SECOND);
    }

    public String createRefreshToken(String userId) {
        return doCreateToken(userId, REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String extendExpireDate(String token) {
        long curTime = System.currentTimeMillis();
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .parseClaimsJws(token);
        return claims.getBody().setExpiration(new Date(curTime + REFRESH_TOKEN_VALIDATION_SECOND)).getSubject();
    }

    public String doCreateToken(String userId, long expireTime) {
        long curTime = System.currentTimeMillis();
        String sessionToken = Jwts.builder()
                .setHeaderParam("typ", "JWT") // JWT Header가 지닐 정보(default : HS256)
                .setExpiration(new Date(curTime + expireTime)) // 만료 시각 (+1일)
                .setIssuedAt(new Date(curTime)) // 발급 시각
                .claim(DATA_KEY, userId) //Payload에 Private Claims를 담기 위해 claim method를 이용
                .signWith(SignatureAlgorithm.HS256, "secret_kyungjoon_is_genius") // (6)
                .compact();
        //세션토큰 발급 완료
        return sessionToken;
    }

    public static String makeJwtToken() {
        Date now = new Date();

        String result= Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("id", "아이디") // (5)
                .claim("email", "ajufresh@gmail.com")
                .signWith(SignatureAlgorithm.HS256, "kyungjoonodidiodig93482347jsdh72354766sdfhu274t23adfd62") // (6)
                .compact();
        return result;
    }



    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //session 에 있는지 확인
    public boolean isUsable(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(getSigningKey(SECRET_KEY))
                    .parseClaimsJws(jwtToken);
            System.out.println(claims.getBody());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰이 유효한 토큰인지 검사한 후, 토큰에 담긴 Payload 값을 가져온다.
    public Claims extractAllClaims(String jwtToken) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    //만료된 토큰인지 체크
    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String getUserId(String token) {

        return extractAllClaims(token).get("userId", String.class);
    }

    public Boolean validateToken(String token, User user) {
        final String userId = getUserId(token);

        String _id= user.getId();

        return (userId.equals(_id) && !isTokenExpired(token));
    }
}
