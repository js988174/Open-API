//package com.project.api.config;
//
//import com.project.api.vo.MemberVo;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.aspectj.weaver.Member;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import java.security.Key;
//
//@RequiredArgsConstructor
//public class AuthResolver implements HandlerMethodArgumentResolver {
//
//    private static final String KEY = "7k+H0TaK9i5MEf88uvsfuyBUzxfM+HX+5WUykOwYLW3uWcI1SH6R5y+jOZq8q0R6KTAvSVIKUkooxxvrwN+ixQ==";
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(MemberVo.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//
//        String jws = webRequest.getHeader("Authorization");
//        if (jws == null || jws.equals("")) {
//            throw new Exception();
//        }
//
//        byte[] decodeKey = Base64.decodeBase64(KEY);
//
//        try {
//            Jws<Claims> claims = Jwts.parserBuilder()
//                    .setSigningKey(decodeKey)
//                    .build()
//                    .parseClaimsJws(jws);
//            String uerId = claims.getBody().getSubject();
//            return null;
//
//        } catch (JwtException e) {
//            throw new Exception();
//        }
//
//    }
//}
