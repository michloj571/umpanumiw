package pl.polsl.umpa;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public abstract class AbstractServiceComponent {
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

        protected WebClient webClient;

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

    protected <T, R> R sendEspRequest(
            String url, T requestBody,
            Class<R> responseBodyType,
            RequestType requestType
    ) {
        return requestType.headerUriSpec(url, requestBody)
                .retrieve()
                .bodyToMono(responseBodyType)
                .block();
    }
}
