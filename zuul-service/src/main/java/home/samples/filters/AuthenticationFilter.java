package home.samples.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import home.samples.dto.UserInfoDto;
import home.samples.interceptors.AuthoriationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class AuthenticationFilter extends ZuulFilter{
    private static final int FILTER_ORDER =  2;
    private static final boolean  SHOULD_FILTER=true;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    @Autowired
    FilterUtils filterUtils;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthTokenPresent() {
        if (filterUtils.getAuthToken() !=null){
            return true;
        }

        return false;
    }

    private UserInfoDto isAuthTokenValid(){
        ResponseEntity<UserInfoDto> restExchange = null;
        try {
            List interceptors = restTemplate.getInterceptors();
            if (interceptors == null) {
                restTemplate.setInterceptors(Collections.singletonList(new AuthoriationInterceptor(filterUtils.getAuthToken())));
            } else {
                interceptors.add(new AuthoriationInterceptor(filterUtils.getAuthToken()));
                restTemplate.setInterceptors(interceptors);
            }

            restExchange =
                    restTemplate.exchange(
                            "http://authentication-service/auth/user",
                            HttpMethod.GET,
                            null, UserInfoDto.class);
        }
        catch(HttpClientErrorException ex){
            if (ex.getStatusCode()== HttpStatus.UNAUTHORIZED) {
                return null;
            }

            throw ex;
        }


        return restExchange.getBody();
    }



    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        if (isAuthTokenPresent()){
            logger.debug("Authentication token is present.");
        }else{
            logger.debug("Authentication token is not present.");

            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setSendZuulResponse(false);
        }

        UserInfoDto userInfo = isAuthTokenValid();
        if (userInfo!=null){
            filterUtils.setUserId(userInfo.getUser());
            logger.debug("Authentication token is valid.");
            return null;
        }

        logger.debug("Authentication token is not valid.");
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        ctx.setSendZuulResponse(false);

        return null;
    }
}
