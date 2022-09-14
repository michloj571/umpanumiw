package pl.polsl.umpa;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;
import pl.polsl.umpa.esp1.poolthermometer.PoolThermometerState;

import javax.annotation.PostConstruct;

public abstract class AbstractServiceComponent {
    private String componentUrl;

    protected enum RequestType {
        GET {
            @Override
            protected HttpMethod method() {
                return HttpMethod.GET;
            }
        }, POST {
            @Override
            protected HttpMethod method() {
                return HttpMethod.POST;
            }
        }, PUT {
            @Override
            protected HttpMethod method() {
                return HttpMethod.PUT;
            }
        }, DELETE {
            @Override
            protected HttpMethod method() {
                return HttpMethod.DELETE;
            }
        };

        protected abstract HttpMethod method();

        protected final WebClient webClient;

        RequestType() {
            this.webClient = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        }

        @SuppressWarnings("unchecked")
        public <S extends WebClient.RequestHeadersSpec<S>, T> WebClient.RequestHeadersSpec<S> headerUriSpec(String url, T requestBody) {
            return (WebClient.RequestHeadersSpec<S>) this.webClient.method(this.method())
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(requestBody));
        }

    }
    protected AbstractServiceComponent(String componentUrl) {
        this.componentUrl = componentUrl;
    }

    protected final EspSetParameterRequest createEspRequest(ComponentState newState) {
        return new EspSetParameterRequest(newState);
    }

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl;
    }

    @PostConstruct
    public abstract void onServerReset();

    protected <T, R> R sendEspRequest(
            RequestType requestType, String url, T requestBody,
            Class<R> responseBodyType
    ) {
        R result;
        try {
            result = requestType.headerUriSpec(url, requestBody)
                    .retrieve()
                    .bodyToMono(responseBodyType)
                    .block();
        } catch (Exception e) {
            throw new ComponentCommunicationFailedException(
                    "Cannot communicate with component: " + componentUrl, HttpStatus.FAILED_DEPENDENCY
            );
        }
        return result;
    }
}
