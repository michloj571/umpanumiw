package pl.polsl.umpa;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.polsl.umpa.AbstractSmartHomeComponentState.ComponentState;

public class AbstractRestBean {
    protected Logger logger;

    protected AbstractRestBean(Logger logger) {
        this.logger = logger;
    }

    protected record WebPageComponent(String title, String value) {
        public static WebPageComponent componentNameField(String componentName) {
            return new WebPageComponent("Component name", componentName);
        }

        public static WebPageComponent componentStateField(String componentState) {
            return new WebPageComponent("Component state", componentState);
        }

        public static WebPageComponent field(String title, Object value) {
            return new WebPageComponent(title, "" + value);
        }
    }

    protected String generateReport(
            String componentName,
            ComponentState componentState,
            WebPageComponent... components
    ) {
        StringBuilder builder = new StringBuilder("<!DOCTYPE html><html>" +
                "<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "<link rel=\"icon\" href=\"data:,\">" +
                "<style>body { text-align: center; font-family: \"Trebuchet MS\", Arial;}" +
                "table { border-collapse: collapse; width:35%; margin-left:auto; margin-right:auto; }" +
                "th { padding: 12px; background-color: #0043af; color: white; }" +
                "tr { border: 1px solid #ddd; padding: 12px; }" +
                "tr:hover { background-color: #bcbcbc; }" +
                "td { border: none; padding: 12px; }" +
                ".sensor { color:white; font-weight: bold; background-color: #bcbcbc; padding: 1px; }" +
                "</style></head><body><h1>Component report</h1>" +
                "<table><tr>REPORT<th></th><th>VALUE</th></tr>"
        ).append(this.newField(WebPageComponent.componentNameField(componentName)))
                .append(this.newField(WebPageComponent.componentStateField(componentState.name())));
        for (WebPageComponent component : components) {
            builder.append(this.newField(component));
        }
        return builder.toString();
    }

    private String newField(WebPageComponent webPageComponent) {
        return "<tr><td>" + webPageComponent.title() + "</td><td><span class=\"sensor\">" +
                webPageComponent.value() +
                "</span></td></tr>";
    }

    @ExceptionHandler({MinorServerException.class})
    public ResponseEntity<?> handleRuntimeException(MinorServerException e) {
        this.logger.error(e.getMessage());
        return ResponseEntity.status(e.getResponseStatus()).build();
    }
}
