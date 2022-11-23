package kr.megaptera.wherewego.interceptors;

import com.auth0.jwt.exceptions.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.utils.*;
import org.springframework.web.servlet.*;

import javax.servlet.http.*;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    public AuthenticationInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            // 토큰 들어온게 없음
            return true;
        }

        String accessToken = authorization.substring("Bearer ".length());

        try {
            String socialLoginId = jwtUtil.decode(accessToken);

            // 1. JWT -> socialLoginId 획득 -> 클레임토큰으로만 사용하겠다는 의미
            // 2. socialLoginId 이용해서 user 확인 -> 좀 더 강화된 보안
            // userRepository.findBySocialLoginId(socialLoginId); 활용
            // 3. socialLoginId JWT가 연결되었는지를 확인 -> 더욱 강화된 보안

            request.setAttribute("socialLoginId", socialLoginId);

            return true;
        } catch(JWTDecodeException exception) {
            throw new AuthenticationError();
        }
    }
}
