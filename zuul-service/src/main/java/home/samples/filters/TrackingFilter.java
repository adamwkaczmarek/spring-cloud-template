package home.samples.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrackingFilter extends ZuulFilter {

    private static final int FILTER_ORDER=1;
    private static final boolean SHOULD_FILTER=true;
    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    FilterUtils filterUtils;

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

    @Override
    public Object run() {
        if(isCorrelationIdPresent()){
            LOGGER.info("correlation-id found in tracking filer {}",filterUtils.getCorrelationId());

        }else {
            String correlationId = generateCorrelationId();
            filterUtils.setCorrelationId(correlationId);
            LOGGER.info("correlation-id generated in tracking filter {} ",correlationId);
        }

        RequestContext requestContext =RequestContext.getCurrentContext();
        LOGGER.debug("Processing incoming request for {} ",requestContext.getRequest().getRequestURI());

        return null;
    }

    private String generateCorrelationId(){
        return UUID.randomUUID().toString();
    }


    private boolean isCorrelationIdPresent(){
        if(filterUtils.getCorrelationId()!=null){
                return true;
        }else{
            return false;
        }
    }
}
